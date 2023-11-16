<script setup>

</script>

<template>
   <div class="pass">
      <div class = "centered">&#9989;</div>
      <h2>ส่งสำเร็จ</h2>
   </div>
</template>

<style scoped>

.centered {
      font-size: 10vw;
   }
.pass{
   background-color: rgba(179, 194, 194, 0.3);
   display: flex; /* เพิ่ม flex เพื่อให้ .centered กลายเป็น flex item */
   flex-direction: column;
   align-items: center; /* จัดตำแหน่งให้ .centered ตรงกลาง */
   justify-content: center; /* จัดตำแหน่งให้ .centered ตรงกลาง */
   height: 60%;
   width: 60%; /* แก้จาก weight เป็น width */
   border-radius: 15px; /* กำหนดขนาดขอบมน */
   overflow: hidden; /* ป้องกันขอบมนที่มากเกินไป */
   background-size: contain;
}

</style>

