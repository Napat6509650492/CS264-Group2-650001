<script setup>
import { ref, watch } from 'vue';
import Pass from '../../components/pass.vue'

const isPopupVisible = ref(false)
var date = new Date()


const formdata = ref({
   "topic": '',
   "too": '',
   "date": `${date.getFullYear()}-${date.getMonth()}-${date.getDay()}`,
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
   "subDistrict": '',
   "province": '',
   "postalCode": '',
   "mobilePhone": '',
   "phone":'',
   "objective":'',
   "cause": '',
   "files": []
})


watch(formdata, async (newdata,olddata)=>{
   console.log(newdata);
},{
   deep:true
})

function handleFileUpload(e) {
      formdata.value.files = e.target.files
   console.log(formdata.value.files[0].name);
}

function submitf(){
   let data = new FormData();

   Object.entries(formdata.value).forEach(([key,value]) => {
      if (key == 'files') {
            Array.from(value).forEach(file => {
            data.append('files', file);
         });
      } else {
         data.append(key, value);
      }
   });

   for (let [key, value] of data) {
         console.log(`${key}: ${value}`);
   }

   var requestOptions = {
      method: 'POST',
      body: data,
      redirect: 'follow',
      
   };

   fetch("http://localhost:8080/api/normal/create", requestOptions)
      .then(response => {
         if (response.status == 200) {
            response.text()
               .then((result) => {
                  console.log(result);
                  isPopupVisible.value = !isPopupVisible.value;
                  formdata.value = {
                     "topic": '',
                     "date": `${date.getFullYear()}-${date.getMonth()}-${date.getDay()}`,
                     "too": '',
                     "addordrop": '',
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
                     "addordrop": "add",
                     "files": [],
                     "subject": {
                        "subjectCode": "",
                        "subjectName": "",
                        "subjectSection": "",
                        "subjectDate": "",
                        "subjectCredit": 0,
                        "subjectTeacher": ""
                     }
                  }
               })
         } else {
            alert('Error something!!')
         }
      })
      .catch(error => console.log('error', error));
}

</script>

<template>
      <div class="main">
         <Pass @click="isPopupVisible = !isPopupVisible" :class="{ 'pass': true, 'popup-visible': isPopupVisible }"></Pass>
         <div class="head-1">คำร้องทั่วไป</div>
         <div class="head-2">คณะวิทยาศาสตร์และเทคโนโลยี มหาวิทยาลัยธรรมศาสตร์ ศูนย์รังสิต</div>

         <form @submit.prevent="submitf">
            <div class="topic">
                  <div>
                     <label for="เรื่อง" v-bind:class="{error:formdata.topic.length > 100 || /\d/.test(formdata.topic)}" class="required">เรื่อง</label>
                     <input type="text"  id="เรื่อง" name="เรื่อง" v-model="formdata.topic" v-bind:class="{error:formdata.topic.length > 100 || /\d/.test(formdata.topic)}" placeholder="เรื่อง" required>
                  </div>
            </div>
            <div > 
                  <div >
                     <label for="เรียน" v-bind:class="{error:formdata.too.length > 100 || /\d/.test(formdata.too)}" class="required">เรียน</label>
                     <input type="text" id="เรียน" name="เรียน" v-model="formdata.too" v-bind:class="{error:formdata.too.length > 100 || /\d/.test(formdata.too)}" placeholder="เรียน" required>
                  </div>
            </div>
            <p>ข้อมูลส่วนตัว</p>
            <div class="title">
                  <div >
                     <div style="display: inline;">
                           <input type="radio" id="นาย" name="คำนำหน้าชื่อ" value="นาย" v-model="formdata.title">
                           <label for="นาย">นาย</label>
                     </div>
                     <div style="display: inline;">
                           <input type="radio" id="นาง" name="คำนำหน้าชื่อ" value="นาง" v-model="formdata.title">
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
                        <label for="first_name" v-bind:class="{error:formdata.studentFirstName.length > 50 || /\d/.test(formdata.studentFirstName)}" class="required">ชื่อ</label>
                           <input type="text" id="first_name" name="first_name" v-model="formdata.studentFirstName" v-bind:class="{error:formdata.studentFirstName.length > 50 || /\d/.test(formdata.studentFirstName)}" placeholder="ชื่อ" required>
                     </div>
                     <div>
                        <label for="last_name" v-bind:class="{error:formdata.studentLastName.length > 50 || /\d/.test(formdata.studentLastName)}" class="required">นามสกุล</label>
                           <input type="text" id="last_name" name="last_name" v-model="formdata.studentLastName" v-bind:class="{error:formdata.studentLastName.length > 50 || /\d/.test(formdata.studentLastName)}" placeholder="นามสกุล" required>
                     </div>
                  </div>

                  <div class="input-row">
                     <div>
                        <label for="เลขทะเบียน" v-bind:class="{error:formdata.studentId.length > 10}" class="required">เลขทะเบียน</label>
                        <input type="text" id="เลขทะเบียน" name="เลขทะเบียน" v-model="formdata.studentId" v-bind:class="{error:formdata.studentId.length > 10}" pattern="[0-9]{10}" maxlength="10" minlength="10" placeholder="เลขทะเบียน" required>
                     </div>

                     <div>
                        <label for="studentYear" class="required">ชั้นปีที่</label>
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
                        <label for="สาขาวิชา" v-bind:class="{error:formdata.studyField.length > 50 || /\d/.test(formdata.studyField)}" class="required">สาขาวิชา</label>
                        <input type="text" id="สาขาวิชา" name="สาขาวิชา" v-model="formdata.studyField" v-bind:class="{error:formdata.studyField.length > 50 || /\d/.test(formdata.studyField)}" placeholder="สาขาวิชา" required>
                     </div>
                     <div>
                        <label for="teacher" v-bind:class="{error:formdata.advisor.length > 100 || /\d/.test(formdata.advisor)}" class="required">อาจารย์ที่ปรึกษา</label>
                        <input type="text" id="teacher" name="teacher" v-model="formdata.advisor" v-bind:class="{error:formdata.advisor.length > 100 || /\d/.test(formdata.advisor)}" placeholder="อาจารย์ที่ปรึกษา" required>
                     </div>
                  </div>
               </div>

            <p >ที่อยู่ที่ติดต่อได้ทางไปรษณีย์</p>
            <div class="address"> 
                  <div class="input-row">
                     <div>
                        <label for="house-number" v-bind:class="{error:formdata.addressNumber.length > 10}" class="required">บ้านเลขที่</label>
                        <input type="text" id="house-number" name="house-number" placeholder="บ้านเลขที่" v-model="formdata.addressNumber" v-bind:class="{error:formdata.addressNumber.length > 10}" required>
                     </div>
                     <div>
                        <label for="village" v-bind:class="{error:formdata.moo.length > 10}" class="required">หมู่</label>
                        <input type="text" id="village" name="village" placeholder="หมู่" v-model="formdata.moo" v-bind:class="{error:formdata.moo.length > 10}" pattern="[0-9]" required>
                     </div>
                  </div>

                  <div class="input-row">
                     <div>
                        <label for="sub-district" v-bind:class="{error:formdata.tumbol.length > 30 || /\d/.test(formdata.advisor)}" class="required">ตำบล/แขวง</label>
                        <input type="text" id="sub-district" name="sub-district" placeholder="ตำบล/แขวง" v-model="formdata.tumbol" v-bind:class="{error:formdata.tumbol.length > 30 || /\d/.test(formdata.advisor)}" required>
                     </div>
                     <div>
                        <label for="district" v-bind:class="{error:formdata.amphur.length > 30 || /\d/.test(formdata.amphur)}" class="required">อำเภอ/เขต</label>
                        <input type="text" id="district" name="district" placeholder="อำเภอ/เขต" v-model="formdata.amphur" v-bind:class="{error:formdata.amphur.length > 30 || /\d/.test(formdata.amphur)}" required>
                     </div>
                  </div>

                  <div class="input-row">
                     <div>
                        <label for="province" v-bind:class="{error:formdata.province.length > 50 || /\d/.test(formdata.province)}" class="required">จังหวัด</label>
                        <input type="text" id="province" name="province" placeholder="จังหวัด" v-model="formdata.province" v-bind:class="{error:formdata.province.length > 50 || /\d/.test(formdata.province)}" required>
                     </div>
                     <div>
                        <label for="postal-code" v-bind:class="{error:formdata.postalCode.length > 5}" class="required">รหัสไปรษณีย์</label>
                        <input type="text" id="postal-code" name="postal-code" placeholder="รหัสไปรษณีย์" v-model="formdata.postalCode" v-bind:class="{error:formdata.postalCode.length > 5}" pattern="[0-9]{5}" maxlength="5" minlength="5" required>
                     </div>
                  </div>
                  <div class="input-row">
                     <div>
                        <label for="mobilePhone" v-bind:class="{error:formdata.mobilePhone.length > 10}" class="required">เบอร์โทรศัพท์</label>
                        <input type="text" id="mobilePhone" name="mobilePhone" placeholder="เบอร์โทรศัพท์" v-model="formdata.mobilePhone" v-bind:class="{error:formdata.mobilePhone.length > 10}" pattern="[0-9]{10}" maxlength="10" minlength="9" required>
                     </div>
                     <div>
                        <label for="phone" v-bind:class="{error:formdata.phone.length > 10}" class="required">เบอร์โทรศัพท์บ้าน</label>
                        <input type="text" id="phone" name="phone" placeholder="เบอร์โทรศัพท์บ้าน" v-model="formdata.phone" v-bind:class="{error:formdata.phone.length > 10 }" pattern="[0-9]{10}" maxlength="10" minlength="0">
                     </div>
                  </div>
            
            
            
            
            </div>
            
            <div ></div>
            <p class="required">ประสงค์ขอ</p>
                     <div>
                           <div>
                              <div>
                                       <input type="radio" id="exit" name="prasongkor"  value="ลาออก" v-model="formdata.objective">
                                       <label for="exit" >ขอลาออก</label>
                              </div>
                              <div>
                                       <input type="radio" id="mfs" name="prasongkor"  value="ผ่อนพันค่าจดทะเบียน" v-model="formdata.objective">
                                       <label for="mfs" >ขอผ่อนผันค่าจดทะเบียนเรียน</label>
                              </div>
                           </div>
                     </div>
            <div class="reason">
                  <label for="reason" class="required">เหตุผล</label>
                  <textarea id="reason" name="reason"  rows="2" cols="25" v-model="formdata.cause"></textarea>
            </div>
      
            <p class="required">แนบเอกสาร</p>

               <label for="file-upload" class="custom-file-upload">Upload Files</label>

               <input type="file" id="file-upload" name="files[]" multiple="multiple" accept="application/pdf" hidden @change="handleFileUpload">
               <div style="display: inline;">
                     <div v-for="i in formdata.files" style="margin-left: 1rem; padding: 2px;">
                        {{ i.name }}
                     </div>
                  </div>

            <br>
            <div class="sub-con">
               <input type="submit" value="ยื่นคำร้อง">
            </div>
         </form>
      </div>
</template>

<style scoped>
.pass{
   position: absolute;
   left: 50%;
   top: 50%;
   transform: translate(-50%, -50%);
   border: 1px solid #ddd;
   background-color: white;
   padding: 20px;
   box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
   transform: translate(-50%, -50%);
   transition: opacity 0.5s, transform 0.5s; /* Transition สำหรับค่า opacity และ transform */
   opacity: 0; /* เริ่มต้นป๊อปอัพจะโปร่งใส */
   visibility: hidden; /* ซ่อนป๊อปอัพเมื่อเริ่มต้น */
}

.popup-visible {
  opacity: 1; /* ทำให้ป๊อปอัพเป็นทึบ */
  visibility: visible; /* แสดงป๊อปอัพ */
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
      position: relative;
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
   .error {
      color: red;
   }
   .required::after {
      content: "*";
      color: red;
    }
</style>
