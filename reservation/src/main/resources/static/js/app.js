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

async function api(path, options = {}) {
  const headers = options.headers || {};
  headers["Content-Type"] = "application/json";

  const token = getToken();
  if (token) headers["Authorization"] = `Bearer ${token}`;

  const res = await fetch(API_BASE + path, { ...options, headers });

  const text = await res.text();

  // If unauthorized -> back to login
  if (res.status === 401) {
    logout();
    return { message: "Session expired. Please login again.", data: null };
  }

  // If forbidden or other error show message
  if (!res.ok) {
    return { message: text || `Request failed (${res.status})`, data: null };
  }

  try {
    return JSON.parse(text);
  } catch {
    return { message: text || "OK", data: null };
  }
}

function toast(id, msg, type="ok"){
  const el = document.getElementById(id);
  if(!el) return;
  el.className = `toast show ${type === "ok" ? "ok" : "err"}`;
  el.textContent = msg;
}