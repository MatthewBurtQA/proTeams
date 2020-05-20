const REQ = new XMLHttpRequest();
let allProgramersButt = document.querySelector("#buttGetAllProgrammers");

function getAllProgrammers() {
  REQ.onload = () => {
    if (REQ.status === 200) {
      console.dir(REQ);
      let responseObject = REQ.response;
      console.log(responseObject);
      let allPTS = JSON.stringify(responseObject);
      document.body.innerHTML = allPTS;
    } if(REQ.status != 200){
        window.alert("Expected code 200, didn't get that though")
    }
  };

  let userOBJ = REQ.open("GET", `http://localhost:8080/programmer/getAllProgrammers/`);
  REQ.setRequestHeader("Content-type", "Application/json");
  REQ.setRequestHeader('Access-Control-Allow-Origin', '*');
  REQ.responseType = "json";
  console.log(userOBJ);
  REQ.send();
}

allProgramersButt.addEventListener("click", getAllProgrammers);