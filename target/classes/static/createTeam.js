 const postTeam = () => {
  let teamName = document.getElementById("teamNameINPUT").value;

  axios({
    method: 'post',
    url: 'http://localhost:8182/team/createTeam',
    data: {
      "teamName" : teamName
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
         <script src="createTeam.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>

