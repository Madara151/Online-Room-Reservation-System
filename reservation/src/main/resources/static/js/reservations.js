if(!getToken()) window.location.href="/index.html";

async function loadRoomTypes(){
  const res = await api("/api/room-types");
  const select = document.getElementById("roomType");
  const rtErr = document.getElementById("rtErr");

  select.innerHTML = "";

  if(!res || !res.data){
    select.innerHTML = `<option value="">(Room types failed to load)</option>`;
    toast("rtErr", res?.message || "Failed to load room types", "err");
    return;
  }

  if(res.data.length === 0){
    select.innerHTML = `<option value="">(No room types in DB)</option>`;
    toast("rtErr", "No room types found. Insert sample room types in DB.", "err");
    return;
  }

  select.innerHTML = `<option value="">Select room type</option>`;
  res.data.forEach(rt=>{
    const opt = document.createElement("option");
    opt.value = rt.id;
    opt.textContent = `${rt.name} (Rate: ${rt.ratePerNight})`;
    select.appendChild(opt);
  });

  // hide error toast if previously shown
  rtErr.className = "toast";
}
loadRoomTypes();

function clearForm(){
  ["resNo","guestName","address","contact","searchNo"].forEach(id=>{
    const el = document.getElementById(id);
    if(el) el.value = "";
  });
  document.getElementById("roomType").value = "";
  document.getElementById("checkIn").value = "";
  document.getElementById("checkOut").value = "";
  toast("msgAdd","Form cleared","ok");
}

async function saveReservation(){
  const reservationNo = document.getElementById("resNo").value.trim();
  const guestName = document.getElementById("guestName").value.trim();
  const address = document.getElementById("address").value.trim();
  const contactNo = document.getElementById("contact").value.trim();
  const roomTypeIdVal = document.getElementById("roomType").value;
  const checkIn = document.getElementById("checkIn").value;   // YYYY-MM-DD from date picker
  const checkOut = document.getElementById("checkOut").value; // YYYY-MM-DD from date picker

  if(!reservationNo || !guestName || !address || !contactNo || !checkIn || !checkOut){
    toast("msgAdd","All fields are required","err");
    return;
  }

  if(!roomTypeIdVal){
    toast("msgAdd","Please select a room type","err");
    return;
  }

  const roomTypeId = Number(roomTypeIdVal);

  const payload = { reservationNo, guestName, address, contactNo, roomTypeId, checkIn, checkOut };

  const res = await api("/api/reservations", {
    method:"POST",
    body: JSON.stringify(payload)
  });

  if(res?.message && res.message.toLowerCase().includes("created")){
    toast("msgAdd", `${res.message} (${res.data})`, "ok");
  } else {
    toast("msgAdd", res?.message || "Failed to save reservation", "err");
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
  document.getElementById("billHint").textContent = `Saved for billing: ${d.reservationNo}`;
}

function copyBillNo(){
  const no = localStorage.getItem("lastReservationNo");
  if(!no){
    toast("msgSearch","Search a reservation first","err");
    return;
  }
  window.location.href = `/billing.html?reservationNo=${encodeURIComponent(no)}`;
}