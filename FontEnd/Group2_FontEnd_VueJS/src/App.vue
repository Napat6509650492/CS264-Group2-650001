<script setup>
import { watchEffect } from 'vue';
import { userInfoStore } from './stores/userinfo';

const info = userInfoStore();

watchEffect(()=>{
    var requestOptions = {
      method: 'GET',
      redirect: 'follow',
      credentials: 'include'
    };

    fetch("http://localhost:8080/api/auth/checkCookie", requestOptions)
      .then(response => {
        if(response.status == 200){
          response.json()
          .then((result)=>{
            console.log(result);
            info.set(result)
            info.login()
          })
        }else{
          alert("Plase Login!!!")
        }
      })
      .catch(error => console.log('error', error));

})
</script>

<template>
  <RouterView />
</template>

<style scoped></style>