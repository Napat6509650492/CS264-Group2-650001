<script setup>
import { ref, watchEffect } from 'vue';

const addDrop = ref([])
const normal = ref([])


watchEffect(() => {
    var requestOptions = {
        method: 'GET',
        redirect: 'follow',
        credential: 'include'
    };

    fetch("http://localhost:8080/api/adddrop/student/6509650492", requestOptions)
        .then(response => response.json())
        .then(result => {
            addDrop.value = result
            console.log(result)

        })
        .catch(error => console.log('error', error));

    fetch("http://localhost:8080/api/normal/student/6509650492", requestOptions)
        .then(response => response.json())
        .then(async result => {
            normal.value = result
            console.log(result)

        })
        .catch(error => console.log('error', error));

}, [])

</script>

<template>
    <div class="rele">
        <div class="list">
            <p> คำร้องทั่วไป </p>
            <div class="item header">
                <div class="id">Form_ID</div>
                <div class="date">วันที่</div>
                <div class="status">สถานะ</div>
                <div class="detail"></div>
            </div>
            <div class="item" v-for="i in normal.filter(item => { item.state == 4 })">
                <div class="id">{{ i.id }}</div>
                <div class="date">{{ i.date }}</div>
                <div class="status">{{ i.state == 1 ? "รอเจ้าหน้าที่ตรวจสอบ" : i.state == 2 ? "รออาจารย์ที่ปรึกษาอนุมัติ" :
                    i.state == 3 ? "รออาจารย์ผู้สอนอนุมิติ" : i.state == 4 ? "รอคณบดีอนุมัติ" : null }}</div>
            </div>
        </div>
        <div class="list">
            <p> คำร้อง เพิ่ม/ถอน </p>
            <div class="item header">
                <div class="id">Form_ID</div>
                <div class="date">วันที่</div>
                <div class="status">สถานะ</div>
                <div class="detail"></div>
            </div>
            <div class="item" v-for="i in addDrop.filter(item=>{item.state == 4})">
                <div class="id">{{ i.id }}</div>
                <div class="date">{{ i.date }}</div>
                <div class="status">{{ i.state == 1 ? "รอเจ้าหน้าที่ตรวจสอบ" : i.state == 2 ? "รออาจารย์ที่ปรึกษาอนุมัติ" :
                    i.state == 3 ? "รออาจารย์ผู้สอนอนุมิติ" : i.state == 4 ? "รอคณบดีอนุมัติ" : null }}</div>
            </div>

        </div>
    </div>
</template>

<style scoped>


button {
    background-color: #FF0800;
    box-shadow: inset;
    box-shadow: 1px 2px 5px rgba(0, 0, 0, 0.543);
    width: 3rem;
    border-radius: 5px;
    padding: 2px;
    border: 1px none;
}

button:hover {
    cursor: pointer;
    color: white;
    background-color: #EF0107;
}

button:active {
    background-color: #E31837;
}

.header {
    font-weight: bold;
}

p {
    font-size: x-large;
    font-weight: bold;
    margin-bottom: 2rem;
    min-width: 312px;
}

.list {
    width: 90%;
    overflow-x: scroll;
}

.item {
    display: grid;
    align-items: center;
    grid-template-columns: 1fr 1fr 3fr 1fr;
    height: 2rem;
    min-width: 312px;
    margin-bottom: 1rem;
    background-color: #d6e0e1;
    border-bottom: 1px solid black;
    gap: 0.5rem;

}

@media (max-width: 760px) {
    .list {
        width: 100%;
    }

    .item {
        font-size: 10px;
        height: auto;
        padding: 0.5rem;
    }
}</style>
