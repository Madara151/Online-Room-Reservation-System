const API_BASE = ""; // same server

function getToken() {
  return localStorage.getItem("token");
}

function setToken(token) {
  localStorage.setItem("token", token);
}

function logout() {
  localStorage.removeItem("token");
  localStorage.removeItem("username");
  localStorage.removeItem("role");
  window.location.href = "/index.html";
}

/**
 * Standard API caller
 * - Works with ApiResponseDTO {message,data}
 * - Works with ErrorResponseDTO {status,error,message,path,timestamp}
 */
async function api(path, options = {}) {
  const headers = options.headers || {};
  headers["Content-Type"] = "application/json";

  const token = getToken();
  if (token) headers["Authorization"] = `Bearer ${token}`;

  let res;
  try {
    res = await fetch(API_BASE + path, { ...options, headers });
  } catch (e) {
    return { message: "Cannot connect to server. Please check if Spring Boot is running.", data: null };
  }

  const text = await res.text();
  let json = null;

  // try parse JSON (both success and error bodies)
  if (text) {
    try { json = JSON.parse(text); } catch { json = null; }
  }

  // ✅ 401 -> logout
  if (res.status === 401) {
    logout();
    return { message: "Session expired. Please login again.", data: null };
  }

  // ❌ non-OK
  if (!res.ok) {
    // If backend returns ErrorResponseDTO
    if (json && typeof json === "object") {
      const msg = json.message || json.error || `Request failed (${res.status})`;
      if (res.status === 403) {
        return { message: "Access denied (403). Please login again or check your role.", data: null };
      }
      return { message: msg, data: null };
    }

    // Plain text error
    if (res.status === 403) {
      return { message: "Access denied (403). Please login again or check your role.", data: null };
    }
    return { message: text || `Request failed (${res.status})`, data: null };
  }

  // ✅ OK response
  if (json) return json;

  // OK but not JSON
  return { message: text || "OK", data: null };
}

function toast(id, msg, type="ok"){
  const el = document.getElementById(id);
  if(!el) return;
  el.className = `toast show ${type === "ok" ? "ok" : "err"}`;
  el.textContent = msg;

  // auto hide after 3 seconds (nice UX)
  clearTimeout(el._t);
  el._t = setTimeout(() => {
    el.className = "toast";
  }, 3000);
}