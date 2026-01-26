const API_BASE = ""; // same server (Spring Boot). If different: "http://localhost:8080"

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

  // If unauthorized -> send back to login
  if (res.status === 401) {
    logout();
    return;
  }

  const text = await res.text();
  try {
    return JSON.parse(text);
  } catch {
    return { message: text, data: null };
  }
}

function toast(id, msg, type="ok"){
  const el = document.getElementById(id);
  el.className = `toast show ${type === "ok" ? "ok" : "err"}`;
  el.textContent = msg;
}