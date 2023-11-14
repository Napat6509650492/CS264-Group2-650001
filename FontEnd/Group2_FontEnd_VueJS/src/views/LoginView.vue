<script setup>
  import { onBeforeMount, onMounted, onUpdated, ref } from 'vue';
  import { userInfoStore } from '../stores/userinfo';
  import { useRouter } from 'vue-router';


  
  const router = useRouter();
  const info = userInfoStore();

  const user = ref('');
  const pass = ref('');

  onMounted(()=>{
    if(info.getLogin){
      router.push({ path: 'student' })
    }
  })

  const login = ()=> {

  var myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");

  var raw = JSON.stringify({
    "UserName": user.value,
    "PassWord": pass.value
  });

  var requestOptions = {
    method: 'POST',
    headers: myHeaders,
    body: raw,
    redirect: 'follow',
    credentials: 'include'
  };

  fetch("http://localhost:8080/api/auth/", requestOptions)
    .then(response => response.json())
    .then(data=>{
      if(data.status == true){
        console.log(data);
        console.log(info.getLogin);
        info.login()
        router.push({ path: data.type })
      }else{
        console.log(data);
        alert(data.message)
      }
    })
    .catch(error => console.log('error', error));

    console.log(document.cookie);

  }

</script>

<template>
  <div class="login-bg">

  </div>
    <div class="log">
      <div class="login-container">
        <div class="logo-container">
          <img @click="test" src="https://cdn.discordapp.com/attachments/1156502394201448478/1168215081658626068/Thammasat_Horz_logo_route1_01.png" alt="Thammasat University Logo">
        </div>
        <form class="login-form" @submit.prevent="login">
            <input v-model="user" type="text" id="username" placeholder="Username" autocomplete="username" required>
            <input v-model="pass" type="password" id="password" placeholder="Password" autocomplete="current-password" required>
          <button type="submit">Login</button>
        </form>
      </div>
    </div>
</template>

<style scoped>
.login-bg{
  position: absolute;
  opacity: 0.5;
  width: 100vw;
  height: 100vh;
  background-image: url("https://cdn.discordapp.com/attachments/1148812021333233699/1172162589120274452/login_background.jpg?ex=655f507a&is=654cdb7a&hm=a8f13808905c5e8c844fd24bf88f45dab1ca1edf4a1385d040177312135554b5&");
  background-repeat: no-repeat;
  background-size: cover;
  z-index: -1;
  }

.log{
  display: flex;
  height: 100vh;
  align-items: center;
}

.login-container {
  display: flex;
  flex-direction: column;
  text-align: center; 
  background: #fff; 
  padding: 2.5rem; 
  padding-bottom: 3rem;
  border-radius: 8px; 
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); 
  max-width: 600px;
  min-width: 400px; 
  margin-bottom: 10rem;
}

.logo-container {
  margin-bottom: 50px;
}

.logo-container img {
  max-width: 100%; 
  min-width: 360px;
  height: auto; 
}

.login-form{
  display: flex;
  flex-direction: column;
  gap: 1rem;
  width: 100%;
}

input {
  height: 2.5rem;
  width: 60%;
  padding-left: 1rem;
}

button {
  width: 60%;
  height: 2.5rem;
  border: none;
  background-color: #d13d42;
}

button:hover{
  cursor: pointer;
  background-color: #ba3b3f;
}

button:active{
  background-color: #d13d42;
}
</style>