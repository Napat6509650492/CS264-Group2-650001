<script setup>
   defineProps({
   name: {
      type: String,
      required: true
   }
})


</script>

<template>
   <button class="button">
      {{ name }}
   </button>
</template>

<style scoped>
   .button{
      position: relative;
      text-align: center;
      width: 100%;
      padding: 10px;
      border: 0;
      background-color: #d9d9d9;
      margin-top: 1.2rem;
      box-shadow: 4px 3px 4px rgba(0, 0, 0, 0.543);
      border-radius: 2px;
   }
   .button:hover{
      background-color: #c9c9c9;
      cursor: pointer;
   }
   .button:active{
      background-color: rgb(201, 201, 201);
   }
   
   .button::before {
      content: '';
      position: absolute;
      top: 0;
      right: 100%; /* Start from the right */
      bottom: 0;
      left: 0;
      background-color: rgb(103, 103, 103);
      transition: right 0.5s ease-in-out;
      opacity: 0.3;
      z-index: 1;
   }

   .button:hover::before {
      right: 0;
   }
</style>
