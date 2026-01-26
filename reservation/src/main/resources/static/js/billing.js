if(!getToken()) window.location.href="/index.html";

const urlParams = new URLSearchParams(window.location.search);
const qNo = urlParams.get("reservationNo");
if(qNo) document.getElementById("billNo").value = qNo;

async function generateBill(){
  const reservationNo = document.getElementById("billNo").value.trim();
  if(!reservationNo){
    toast("msgBill","Enter reservation number","err");
    return;
  }

  const res = await api(`/api/billing/${encodeURIComponent(reservationNo)}`);

  if(res?.data){
    toast("msgBill","Bill generated","ok");
    showBill(res.data);
  }else{
    toast("msgBill", res?.message || "Bill failed", "err");
    document.getElementById("billCard").style.display="none";
  }
}

function showBill(b){
  const body = document.getElementById("billBody");
  body.innerHTML = `
    <tr><th>Reservation No</th><td>${b.reservationNo}</td></tr>
    <tr><th>Guest Name</th><td>${b.guestName}</td></tr>
    <tr><th>Room Type</th><td>${b.roomTypeName}</td></tr>
    <tr><th>Nights</th><td>${b.nights}</td></tr>
    <tr><th>Rate / Night</th><td>${b.ratePerNight}</td></tr>
    <tr><th>Total Amount</th><td><b>${b.totalAmount}</b></td></tr>
  `;
  document.getElementById("billCard").style.display="block";
}

function printBill(){
  const card = document.getElementById("billCard");
  if(card.style.display === "none"){
    toast("msgBill","Generate a bill first","err");
    return;
  }
  window.print();
}