if(!getToken()) window.location.href="/index.html";

function getRange(){
  const from = document.getElementById("fromDate").value.trim();
  const to = document.getElementById("toDate").value.trim();
  if(!from || !to){
    toast("msgRep","Enter from and to dates","err");
    return null;
  }
  return {from, to};
}

async function loadBookings(){
  const r = getRange();
  if(!r) return;

  const res = await api(`/api/reports/bookings?from=${encodeURIComponent(r.from)}&to=${encodeURIComponent(r.to)}`);

  const tbody = document.querySelector("#bookTable tbody");
  tbody.innerHTML = "";

  if(res?.data){
    toast("msgRep","Bookings loaded","ok");
    res.data.forEach(b=>{
      const tr = document.createElement("tr");
      tr.innerHTML = `<td>${b.reservationNo}</td><td>${b.guestName}</td><td>${b.roomTypeName}</td><td>${b.checkIn}</td><td>${b.checkOut}</td>`;
      tbody.appendChild(tr);
    });
  } else {
    toast("msgRep", res?.message || "Failed to load bookings", "err");
  }
}

async function loadRevenue(){
  const r = getRange();
  if(!r) return;

  const res = await api(`/api/reports/revenue?from=${encodeURIComponent(r.from)}&to=${encodeURIComponent(r.to)}`);

  if(res?.data){
    toast("msgRep","Revenue calculated","ok");
    const d = res.data;
    const body = document.getElementById("revBody");
    body.innerHTML = `
      <tr><th>From</th><td>${d.fromDate}</td></tr>
      <tr><th>To</th><td>${d.toDate}</td></tr>
      <tr><th>Total Bookings</th><td>${d.totalBookings}</td></tr>
      <tr><th>Total Revenue</th><td><b>${d.totalRevenue}</b></td></tr>
    `;
  } else {
    toast("msgRep", res?.message || "Failed to calculate revenue", "err");
  }
}