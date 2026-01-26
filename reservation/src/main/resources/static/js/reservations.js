if(!getToken()) window.location.href="/index.html";

async function loadRoomTypes(){
  const res = await api("/api/room-types");
  const select = document.getElementById("roomType");
  select.innerHTML = "";

  (res?.data || []).forEach(rt=>{
    const opt = document.createElement("option");
    opt.value = rt.id;
    opt.textContent = `${rt.name} (Rate: ${rt.ratePerNight})`;
    select.appendChild(opt);
  });

  if((res?.data || []).length === 0){
    toast("msgAdd","No room types found. Insert room types in DB.","err");
  }
}
loadRoomTypes();

function clearForm(){
  ["resNo","guestName","address","contact","checkIn","checkOut"].forEach(id=>{
    document.getElementById(id).value = "";
  });
  toast("msgAdd","Form cleared","ok");
}

async function saveReservation(){
  const reservationNo = document.getElementById("resNo").value.trim();
  const guestName = document.getElementById("guestName").value.trim();
  const address = document.getElementById("address").value.trim();
  const contactNo = document.getElementById("contact").value.trim();
  const roomTypeId = Number(document.getElementById("roomType").value);
  const checkIn = document.getElementById("checkIn").value.trim();
  const checkOut = document.getElementById("checkOut").value.trim();

  if(!reservationNo || !guestName || !address || !contactNo || !checkIn || !checkOut){
    toast("msgAdd","All fields are required","err");
    return;
  }

  const payload = { reservationNo, guestName, address, contactNo, roomTypeId, checkIn, checkOut };

  const res = await api("/api/reservations", {
    method:"POST",
    body: JSON.stringify(payload)
  });

  if(res?.message && res.message.toLowerCase().includes("created")){
    toast("msgAdd", `${res.message} (${res.data})`, "ok");
  } else if(res?.message) {
    toast("msgAdd", res.message, "err");
  } else {
    toast("msgAdd","Failed to save reservation","err");
  }
}

async function searchReservation(){
  const reservationNo = document.getElementById("searchNo").value.trim();
  if(!reservationNo){
    toast("msgSearch","Enter a reservation number","err");
    return;
  }

  const res = await api(`/api/reservations/${encodeURIComponent(reservationNo)}`);

  if(res?.data){
    toast("msgSearch","Reservation found","ok");
    showDetails(res.data);
  } else {
    toast("msgSearch", res?.message || "Not found", "err");
    document.getElementById("details").style.display="none";
  }
}

function showDetails(d){
  const body = document.getElementById("detailBody");
  body.innerHTML = `
    <tr><th>Reservation No</th><td>${d.reservationNo}</td></tr>
    <tr><th>Guest Name</th><td>${d.guestName}</td></tr>
    <tr><th>Address</th><td>${d.address}</td></tr>
    <tr><th>Contact No</th><td>${d.contactNo}</td></tr>
    <tr><th>Room Type</th><td>${d.roomTypeName}</td></tr>
    <tr><th>Rate/Night</th><td>${d.ratePerNight}</td></tr>
    <tr><th>Check-in</th><td>${d.checkIn}</td></tr>
    <tr><th>Check-out</th><td>${d.checkOut}</td></tr>
  `;
  document.getElementById("details").style.display="block";
  localStorage.setItem("lastReservationNo", d.reservationNo);
  document.getElementById("billHint").textContent = `Saved as lastReservationNo = ${d.reservationNo}`;
}

function copyBillNo(){
  const no = localStorage.getItem("lastReservationNo");
  if(!no){
    toast("msgSearch","Search a reservation first","err");
    return;
  }
  window.location.href = `/billing.html?reservationNo=${encodeURIComponent(no)}`;
}