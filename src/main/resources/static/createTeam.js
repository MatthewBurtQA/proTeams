 const postTeam = () => {
  let name = document.getElementById("teamNameINPUT").value;

  axios({
    method: 'post',
    url: 'http://localhost:8080/team/createTeam',
    data: {
      "name" : name
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

let buttCreateTeam = document.querySelector('#buttCreateTeam');
buttCreateTeam.addEventListener('click', postTeam);
