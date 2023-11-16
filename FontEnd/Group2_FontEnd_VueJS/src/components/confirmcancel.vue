<script setup>
const props = defineProps({
   type: String,
   id: String,
   popcon: Boolean
})

const deleteForm = () => {
   var requestOptions = {
      method: 'DELETE',
      redirect: 'follow',
      credential: 'include'
   };

   fetch(`http://localhost:8080/api/${props.type}/${props.id}`, requestOptions)
      .then(response => response.text())
      .then(result => alert(result))
      .catch(error => console.log('error', error));
}

</script>

<template>
   <div class="confirmcancel">
      <div class="label-container">
         <p class="cancel-label" for="cancel" >ต้องการยกเลิกหรือไม่?</p>
         <h3>Form ID : {{ id }}</h3>
         <br>
         <p2> 
         <button @click="deleteForm" class="confirm-button" type="submit" value="ยืนยัน">ยืนยัน</button>
         <label> &nbsp;&nbsp;&nbsp;</label>
         <button @click="$emit('update',!popcon)" class="cancel-button" type="submit" value="ยกเลิก">ยกเลิก</button>
         </p2>
      
      </div>
   </div>
</template>

<style scoped>
   .hb {
      font-size: 10vw;
      
   }
   
   h3{
      background-color: aliceblue;
      border-radius: 0.5rem;
      padding: 0.5rem;
   }

   .confirmcancel{
   background-color: rgba(179, 194, 194, 0.5);
   display: flex; /* เพิ่ม flex เพื่อให้ .centered กลายเป็น flex item */
   align-items: center; /* จัดตำแหน่งให้ .centered ตรงกลาง */
   justify-content: center; /* จัดตำแหน่งให้ .centered ตรงกลาง */
   height: 50vh;
   width: 30vw; /* แก้จาก weight เป็น width */
   border-radius: 15px; /* กำหนดขนาดขอบมน */
   overflow: hidden; /* ป้องกันขอบมนที่มากเกินไป */
   background-size: contain;
   margin: 0 auto;
   padding: 0px;
   
}

.confirm-button {
    background-color: rgb(70, 231, 70); /* กำหนดสีเขียว */
    color: white; /* กำหนดสีข้อความเป็นขาว */
    padding: 10px 20px; /* เพิ่มเว้นขอบของปุ่ม */
    border: none; /* กำหนดไม่มีเส้นขอบ */
    border-radius: 5px; /* กำหนดขนาดขอบมน */
    cursor: pointer;
    font-size: xx-large;
    margin: 0 auto;
}

.cancel-button {
    background-color: rgb(229, 65, 65); /* กำหนดสีเขียว */
    color: white; /* กำหนดสีข้อความเป็นขาว */
    padding: 10px 20px; /* เพิ่มเว้นขอบของปุ่ม */
    border: none; /* กำหนดไม่มีเส้นขอบ */
    border-radius: 5px; /* กำหนดขนาดขอบมน */
    cursor: pointer;
    font-size: xx-large;
    margin: 0 auto;
}

.cancel-label {
    font-size: 2.5em; /* ปรับขนาดข้อความใหญ่ขึ้น */
    align-items: center; 
   justify-content: center; 
   margin: 0 auto;
   padding: 0px;

}

.label-container {
    display: flex; /* เพิ่ม display: flex เพื่อให้ label อยู่ในบรรทัดเดียวกับปุ่ม */
    flex-direction: column; /* แนวตั้ง */
    align-items: center; /* จัดให้อยู่กึ่งกลาง */
    margin-bottom: 10px; /* เพิ่ม margin ด้านล่าง */
    margin: 0 auto;
    padding: 0px;
}

</style>

