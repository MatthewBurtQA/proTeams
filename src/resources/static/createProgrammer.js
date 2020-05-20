 const postProgrammer = () => {
  let firstName = document.getElementById("firstNameINPUT").value;
  let lastName = document.getElementById("lastNameINPUT").value;
  let language = document.getElementById("languageINPUT").value;
  let teamRole = document.getElementById("teamroleINPUT").value;
  
  axios({
    method: 'post',
    url: 'http://localhost:8182/programmer/createProgrammer',
    data: {
      "firstName" : firstName,
      "lastName" : lastName,
      "language" : language,
      "teamRole" : teamRole
    },
    headers: {'content-Type': 'application/json'}
  })
  .then(function (response) {
    console.log(response);
  })
  .catch(function (response) {
    console.log(response);
  })
}

let buttCreateProgrammer = document.querySelector('#buttCreateProgrammer');
buttCreateProgrammer.addEventListener('click', postProgrammer);
