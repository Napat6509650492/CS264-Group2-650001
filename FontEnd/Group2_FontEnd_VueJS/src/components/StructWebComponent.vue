<script setup>
   import Cookies from 'js-cookie'

   import { userInfoStore } from '../stores/userinfo';
   import { ref } from 'vue';
   import { useRouter } from 'vue-router';

   const userinfo = userInfoStore();
   const barsideActive = ref(false);
   const logout = ref(false);
   const router = useRouter();

   function logout_user() {
      var requestOptions = {
      method: 'GET',
      redirect: 'follow',
      credentials:'include'
   };

   fetch("http://localhost:8080/api/auth/logout", requestOptions)
      .then(response => response.text())
      .then(result => {
         router.push({name:'login'})
      })
      .catch(error => console.log('error', error));
   }
</script>

<template>
   <div class="fixed-placeholder"></div>
   <header class="">
      <div class="head">
         <div class="container-logo">
            <img alt="Vue logo" class="logo"
               src="https://cdn.discordapp.com/attachments/1156502394201448478/1168215081658626068/Thammasat_Horz_logo_route1_01.png?ex=6550f413&is=653e7f13&hm=295061da163ad864747f5031eff06ab5f9f4608dc4ce52e67439917abd443c9d&" />
         </div>
      </div>
      <div class="bar">
      <div class="hambar">
         <div @click="()=>{
            barsideActive = barsideActive? false:true;
         }" class="hamburger-menu">
            <div class="hbar"></div>
            <div class="hbar"></div>
            <div class="hbar"></div>
         </div>
      </div>
         <div class="account" style="position: relative;">
            <div class="account-c1" @click="()=>{ 
               logout = !logout;
            }">
               <div style="display: inline-block;" class="gg-profile"></div>
               <div style="display: inline-block;">{{ userinfo.getInfo.displayname_th }}
               </div>
            </div>
            <div class="logout" v-bind:hidden="!logout" @click="logout_user">Logout</div>
         </div>
      </div>
   </header>
   <div class="sidebar" :class="{ hideSidebar : barsideActive}">
      <div class="menu-list" style="height: 100%;">
         <slot name="btn-list">
         </slot>
      </div>
   </div>
   <div class="body" :class="{ hideM: !barsideActive }">
      <slot name="body"></slot>
   </div>
</template>

<style scoped>
@import url('https://unpkg.com/css.gg@2.0.0/icons/css/profile.css');

.isClick {
   background-color: #a0a0a0;
}

.hideSidebar{
   display: none;
}

.hideM{
   margin-left: 200px;
}

.as {
   margin-bottom: 20px;
}

.button {
   background-color: #d9d9d9;
   width: 100%;
}

.button:hover {
   background-color: #acacac;
}


.body {
   height: fit-content;
   padding: 2rem;
}

.fixed-placeholder {
   height: 169.26px;
   /* ขนาดเท่ากับองค์ประกอบที่ fixed */
   visibility: hidden;
   /* ทำให้ไม่มองเห็นแต่ยังคงพื้นที่ */
}

.sidebar {
   position: fixed;
   width: 200px;
   height: 100%;
   background-color: #ee826d;
   margin: 0 auto;
   background-color: #dc2626;
   border-top: 0.5px solid black;
   z-index: 99;
}

@media (max-width: 760px) {
   .sidebar{
      width: 150px;
   }
   .body{
      margin-left: 0px;
   }

   .account{
      margin-right: 0px;
      font-size: 13px;
      font-weight: 100;
   }
}

.logo {
   height: 70px;
   margin-left: 1rem;
}

.head {
   width: 100%;
   padding-top: 1rem;
   padding-bottom: 1rem;
   border-bottom: black solid 1px;
   background-image: linear-gradient(to right, #ffe70a, #ff4848);
}

header {
   position: fixed;
   width: 100%;
   top: 0;
   z-index: 10;
}

.container-logo {
   background-color: white;
   padding: 10px;
}

.bar {
   display: flex;
   background-color: #de2424f7;
   box-shadow: 0px 5px 10px 1px rgba(78, 78, 78, 0.75);
   /* background-image: linear-gradient(to bottom, #ffa353, #ff422d); */
}

.account {
   display: flex;
   gap: 0.5rem;
   height: 100%;
   max-height: 40px;
   padding: 11px;
   text-align: center;
   margin-right: 2rem;
   color: white;
   font-weight: bold;
}
.account-c1:active{
   color: #ffffff;
}
.account-c1:hover{
   color: #ededed;
}

.account-c1{
   display: flex;
   position: relative;
   gap: 0.5rem;
   cursor: pointer;
}

.account-c1:hover::before{
   content: " ";
   position: absolute;
   height: 1px;
   width: 100%;
   bottom: -8px;
   background-color: #ffffff;
}

.logout{
   background-color: rgb(174, 174, 174); 
   color: #000000;
   border: 0px solid black;
   border-radius: 0.3rem;
   font-weight: 100;
   position: absolute;
   width: 100%; 
   top: 100%; 
   left: 0px; 
   padding: 0.25rem; 
   margin-top: 3px;
}

.logout:hover{
   cursor: pointer;
   background-color: #dfdfdf;
   
}

.logout:active{
   color: #343434;
   background-color: #c6c6c6;
}

.hamburger-menu {
   display: flex;
   margin: 0px;
   flex-direction: column;
   justify-content: space-between;
   height: 20px; 
   cursor: pointer;
   z-index: 2;
}

.hambar{
   margin: 0px;
   margin-top: 0.5rem;
   width: 200px;
}

.hbar {
  height: 2px; 
  width: 30px;
  background-color: #000000; 
  border-radius: 20px; 
  transition: all 0.3s ease;
  z-index: 30;
}

</style>