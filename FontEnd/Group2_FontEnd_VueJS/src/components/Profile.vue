<script setup>
import { userInfoStore } from '../stores/userinfo';

const info = userInfoStore();
console.log(info.getInfo);

</script>



<template>
<div class="container">
   <p class="front-bold p1"><img 
      src="https://cdn.discordapp.com/attachments/998545526830141501/1172541990609616977/Pngtreeavatar_icon_profile_icon_member_5247852.png?ex=6560b1d3&is=654e3cd3&hm=ad5b3da139ff13142541bbdeae80fb79b31728cee183cef4593574b1630ae765&" class="imgpf">โปรไฟล์</p>
      <div class="bginfo" style="width: fit-content; min-width: 300px;">
         <p>สถานะ : {{ info.getInfo.type }}</p> 
         <p>ชื่อ-นามสกุล : {{ info.getInfo.displayname_th }}</p>
         <p>ชื่อภาษาอังกฤษ : {{ info.getInfo.displayname_en }}</p>
         <p>เลขนักศึกษา : {{ info.getInfo.username }}</p>
         <p>คณะ : {{ info.getInfo.department }}</p>
         <p>สาขา : {{ info.getInfo.faculty }}</p>
         <p>Email : {{ info.getInfo.email }}</p>
      </div>
</div>
</template>
<style scoped>
      .bginfo {
   max-width: 50vw; 
   height: 25vw; 
   background-color: #e0e0e0;
   border-radius: 10px; 
   margin-left: 60px;
   padding: 1px;
   height: fit-content;
   box-shadow: 0 4px 12px rgba(0, 0, 0, 0.25); 
}
      .p1 {
   font-size: 30px;
   font-weight: bold;
   margin-bottom: 10px;
   min-width: 312px;
   margin-left: 60px;
   text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.35); 
}

      p{
   font-size: 25px;
   font-weight: bold;
   min-width: 312px;
   padding: 1rem;
   
} 
      img {
   max-width: 10%;
   max-height: 10%;
   height: auto;
   }
      .imgpf{
   float:left;
   width:50px;
   height:50px;
}

@media (max-width: 480px) {
   p{
      font-size: smaller;
   }
}
</style>
