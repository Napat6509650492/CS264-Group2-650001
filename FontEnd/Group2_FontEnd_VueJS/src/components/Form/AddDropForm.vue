<script setup>
import { ref, watch } from 'vue';

const formdata = ref({
   "topic": '',
   "date":'2023-12-23',
   "to": '',
   "addordrop":'',
   "title": '',
   "studentFirstName": '',
   "studentLastName": '',
   "studentId": '',
   "studentYear": '',
   "studyField": '',
   "advisor": '',
   "addressNumber": '',
   "moo": '',
   "tumbol": '',
   "amphur": '',
   "province": '',
   "postalCode": '',
   "mobilePhone": '',
   "phone": '',
   "cause": '',
   "addordrop":"add",
   "files": [],
   "subject": {
      "subjectCode":"132",
      "subjectName":"456",
      "subjectSection":"897",
      "subjectDate":"465",
      "subjectCredit":0,
      "subjectTeacher":"123"
   }
})

function handleFileUpload(e) {
   formdata.value.files = e.target.files
   console.log(formdata.value.files[0].name);
}

watch(formdata, async (newdata, olddata) => {
   console.log(newdata);
   console.log(JSON.stringify(formdata.value.subject));
}, {
   deep: true
})

function submitf() {
   let data = new FormData();
   console.log(formdata.value.subject);
   Object.entries(formdata.value).forEach(([key, value]) => {
      if (key == 'files') {
         Array.from(value).forEach(file => {
            data.append('files', file);
         });
         
      }else if(key == 'subject'){
         data.append(key,JSON.stringify(value))
      } else {
         data.append(key, value);
      }
   });

   for (let [key, value] of data) {
      console.log(`${value}`);
   }

   var requestOptions = {
      method: 'POST',
      body: data,
      redirect: 'follow',
      Credential: 'include'

   };

   fetch("http://localhost:8080/api/adddrop/create", requestOptions)
      .then(response => response.text())
      .then(result => console.log(result))
      .catch(error => console.log('error', error));
}

</script>

<template>
      <div class="main">
         <div class="head-1">คำร้อง เพิ่ม/ถอน</div>
         <div class="head-2">คณะวิทยาศาสตร์และเทคโนโลยี มหาวิทยาลัยธรรมศาสตร์ ศูนย์รังสิต</div>
         <form @submit.prevent="submitf">
            <div class="topic">
                  <div>
                     <label for="เรื่อง" >เรื่อง</label>
                     <input type="text" id="เรื่อง" name="เรื่อง" v-model="formdata.topic">
                  </div>
            </div>
      
            <div > 
                  <div >
                     <label for="เรียน" >เรียน</label>
                     <input type="text" id="เรียน" name="เรียน" v-model="formdata.to">
                  </div>
            </div>
            <p>ข้อมูลลส่วนตัว</p>
            <div class="title">
                  <div >
                     <div style="display: inline;">
                           <input type="radio" id="นาย" name="คำนำหน้าชื่อ" value="นาย" v-model="formdata.title">
                           <label for="นาย">นาย</label>
                     </div>
                     <div style="display: inline;">
                           <input type="radio" id="นาง" name="คำนำหน้าชื่อ" v-model="formdata.title">
                           <label for="นาง" >นาง</label>
                     </div>
                     <div style="display: inline;">
                           <input type="radio" id="นางสาว" name="คำนำหน้าชื่อ"  value="นางสาว" v-model="formdata.title">
                           <label for="นางสาว" >นางสาว</label>
                     </div>
                  </div>
            </div>
            
            <div class="profile">
                  <div class="input-row">
                     <div>
                           <label for="first_name" >ชื่อ</label>
                           <input type="text" id="first_name" name="first_name" v-model="formdata.studentFirstName">
                     </div>
                     <div>
                           <label for="last_name">นามสกุล</label>
                           <input type="text" id="last_name" name="last_name" v-model="formdata.studentLastName">
                     </div>
                  </div>

                  <div class="input-row">
                     <div>
                        <label for="เลขทะเบียน" >เลขทะเบียน</label>
                        <input type="text" id="เลขทะเบียน" name="เลขทะเบียน" v-model="formdata.studentId">
                     </div>

                     <div>
                        <label for="studentYear" >ชั้นปีที่</label>
                        <select id="studentYear" name="studentYear" v-model="formdata.studentYear">
                           <option value="1">1</option>
                           <option value="2">2</option>
                           <option value="3">3</option>
                           <option value="4">4</option>
                           <option value="5">5</option>
                           <option value="6">6</option>
                           <option value="7">7</option>
                           <option value="8">8</option>
                        </select>
                     </div>
                  </div>

                  <div class="input-row">
                     <div>
                        <label for="สาขาวิชา" >สาขาวิชา</label>
                        <input type="text" id="สาขาวิชา" name="สาขาวิชา" v-model="formdata.studyField">
                     </div>
                     <div>
                        <label for="teacher" >อาจารย์ที่ปรึกษา</label>
                        <input type="text" id="teacher" name="teacher" v-model="formdata.advisor">
                     </div>
                  </div>
               </div>

            <p >ที่อยู่ที่ติดต่อได้ทางไปรษณีย์</p>
            <div class="address"> 
                  <div class="input-row">
                     <div>
                        <label for="subjectn">บ้านเลขที่</label>
                        <input type="text" id="house-number" name="house-number" placeholder="บ้านเลขที่" v-model="formdata.addressNumber">
                     </div>
                     <div>
                        <label for="subjectn">หมู่</label>
                        <input type="text" id="village" name="village" placeholder="หมู่" v-model="formdata.moo">
                     </div>
                  </div>

                  <div class="input-row">
                     <div>
                        <label for="subjectn">ตำบล/แขวง</label>
                        <input type="text" id="sub-district" name="sub-district" placeholder="ตำบล/แขวง" v-model="formdata.tumbol">
                     </div>
                     <div>
                        <label for="subjectn">อำเภอ/เขต</label>
                        <input type="text" id="district" name="district" placeholder="อำเภอ/เขต" v-model="formdata.amphur">
                     </div>
                  </div>

                  <div class="input-row">
                     <div>
                        <label for="subjectn">จังหวัด</label>
                        <input type="text" id="province" name="province" placeholder="จังหวัด" v-model="formdata.province">
                     </div>
                     <div>
                        <label for="subjectn">รหัสไปรษณีย์</label>
                        <input type="text" id="postal-code" name="postal-code" placeholder="รหัสไปรษณีย์" v-model="formdata.postalCode">
                     </div>
                  </div>
            </div>
            
            <div ></div>
            <p >เพิ่ม/ถอนรายวิชา</p>
                     <div>
                           <div>
                              <div class="input-container">
                                 <div>
                                    <label for="subjectn">รหัสวิชา</label>
                                    <input type="text" v-model="formdata.subject.subjectCode">
                                 </div>

                                 <div>
                                    <label for="subjectt">ชื่อวิชา</label>
                                    <input type="text" v-model="formdata.subject.subjectName">
                                 </div>
                              </div>
                           
                           <div class="input-container">
                                 <div>
                                    <label for="Section">Section</label>
                                    <input type="text" v-model="formdata.subject.subjectSection">
                                 </div>

                                 <div>
                                    <label for="time">วัน/เวลา</label>
                                    <input type="text" v-model="formdata.subject.subjectDate">
                                 </div>
                           </div>

                           <div class="input-container">
                                 <div>
                                    <label for="credit">หน่วยกิต</label>
                                    <input type="text" v-model="formdata.subject.subjectCredit">
                                 </div>

                                 <div>
                                    <label for="nameteacher">ชื่อผู้สอน</label>
                                    <input type="text" v-model="formdata.subject.subjectTeacher">
                                 </div>
                           </div>
                           </div>
                     </div>
            <div class="reason">
                  <label for="reason" >เหตุผล</label>
                  <textarea id="reason" name="reason"  rows="2" cols="25" v-model="formdata.cause"></textarea>
            </div>
      
            <p>แนบเอกสาร</p>

               <label for="file-upload" class="custom-file-upload">Upload Files</label>
               <input type="file" id="file-upload" name="files[]" multiple="multiple" accept="*" hidden @change="handleFileUpload">
               <div v-for="i in formdata.files" style="margin-left: 1rem; padding: 2px;">
                           {{ i.name }}
               </div>
            <br>
            <div class="sub-con">
               <input type="submit" value="ยื่นคำร้อง">
            </div>
         </form>
      </div>
</template>

<style scoped>

p{
      font-weight: bold;
      font-size: x-large;
      margin-bottom: 1rem;
   }

   input[type="radio"]{
      margin-right: 1rem;
   }


   .address label {
   display: block;
   margin-top: 20px;
   }

   .input-container{
      display: flex;
   }

   .input-container > div{
      width: 100%;
      max-width: 566px;
   }
   .requ::after{
      content: "*";
      color: red;
   }

   label[for="เรื่อง"],label[for="เรียน"]{
      display: block;
   }

   #เรื่อง,#เรียน {
      width: 50%;
   }
   .main{
      min-width: 811px;
      max-width: 1200px;
      border: 2px solid black;
      padding: 2rem;
      border-radius: 2rem;
   }

   .head-1{
      text-align: center;
      margin-bottom: 1rem;
      font-size: x-large;
      font-weight: bold;
      
   }
   .head-2{
      text-align: center;
      margin-bottom: 1rem;
      font-size: x-large;
      
   }

   
   .topic{
      margin-bottom: 0.5rem;
   }

   form{
      margin-top: 3rem;
   }
   form > div{
      margin-bottom: 1rem;
   }

   
   .profile{
      display: flex;
      flex-direction: column;
   }
   .profile > div{
      margin: 0px;
   }

   .profile label{
      display: block;
      margin-top: 20px;
   }
   
   input[type="radio"]{
      margin-right: 0.5rem;
   }
   
   p{
      font-weight: bold;
      font-size: x-large;
      margin-bottom: 1rem;
   }
   
   #studentYear{
      width: 75px;
   }
   
   .address label{
      display: block;
      margin-top: 20px;
   }
   .input-container{
   display: flex;
   }  
   .input-row {
      display: flex;
      justify-content: space-between;
      margin-bottom: 10px;
   }
   .input-row > div{
      width: 100%;
      max-width: 566px;
      
   }

   input[type="text"],
   textarea , select{
   width: calc(100% - 22px);
   padding: 10px;
   margin-top: 5px;
   border: 1px solid #ccc;
   border-radius: 5px;
   }

   /* Style the textarea */
   textarea {
   resize: vertical;
   }

   .sub-con{
      height: 2rem;
   }
   /* Additional styling for the submit button, if you have one */
   input[type="submit"] {
      background-color: #007bff;
      color: white;
      padding: 10px 20px;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      margin: 1rem;
   }

   input[type="submit"]:hover {
      background-color: #0056b3;
   }

   .reason{
      display: flex;
      flex-direction:column;
      gap: 1rem;
   }

   .reason > label,textarea{
      margin: 0px;
   }
   
   .reason > textarea
   {
      margin-left: 1rem;
   }

   input[type="submit"]{
      float: right;
   }

   .upload-container {
      margin: 50px;
      display: flex;
      justify-content: center;
      align-items: center;
   }

   .custom-file-upload {
         border: 1px solid #ccc;
         display: inline-block;
         padding: 6px 12px;
         cursor: pointer;
         background-color: #f8f8f8;
         border-radius: 4px;
         transition: all 0.3s ease;
      }

   .custom-file-upload:hover {
         background-color: #e0e0e0;
      }

   @media (max-width: 760px) {
      .main{
         min-width: 100px;
         max-width:500px
      }

      #เรื่อง,#เรียน {
      margin: 0px;
   }

      input[type="text"]{
         display: block;
      }
      
      label[for="last_name"]{
         margin-left: 0px;
      }
      
      label[for="สาขาวิชา"]{
         margin-top: 1rem;
         margin-left: 0px;
         display: block;
      }
      .title{ flex-wrap: nowrap;}

      

   .address{
      display: block;
   }

   .address > div{
      display: flex;
      justify-content: left;
      flex-direction: column;
      margin-bottom: 1rem;
   }

   .address > div > label{
      margin: 0px;
   }

   .address > div > input{

      float: left;
   }
   
   #studentYear{
      margin-left: 11px;
   }
}
</style>
