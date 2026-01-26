async function login(){
  const username = document.getElementById("username").value.trim();
  const password = document.getElementById("password").value.trim();

  if(!username || !password){
    toast("msg","Username and password are required","err");
    return;
  }

  const res = await api("/api/auth/login", {
    method:"POST",
    body: JSON.stringify({ username, password })
  });

  if(res?.data?.token){
    setToken(res.data.token);
    localStorage.setItem("username", res.data.username);
    localStorage.setItem("role", res.data.role);
    window.location.href = "/dashboard.html";
  }else{
    toast("msg", res?.message || "Login failed", "err");
  }
}

async function register(){
  const username = document.getElementById("username").value.trim();
  const password = document.getElementById("password").value.trim();

  if(!username || !password){
    toast("msg","Enter username + password then click Register","err");
    return;
  }

  const res = await api("/api/auth/register", {
    method:"POST",
    body: JSON.stringify({ username, password })
  });

  if(res?.message){
    toast("msg", res.message, "ok");
  }else{
    toast("msg", "Register failed", "err");
  }
}