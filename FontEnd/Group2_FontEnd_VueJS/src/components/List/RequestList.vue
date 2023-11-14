<script setup>
import { ref } from 'vue';

defineProps({
    
})

</script>

<template>
    <div class="list">
        <p> สถานะคำร้อง </p>
        <div class="item" v-for="i in test">
            <div class="time">{{ i.a }}</div>
            <div class="date">{{ i.b }}</div>
            <div class="status">{{ i.c }}</div>
            <div class="detail">{{ i.d }}</div>
        </div>
    </div>
</template>

<style scoped>

p{
    font-size: x-large;
    font-weight: bold;
    margin-bottom: 2rem;
    min-width: 312px;
}
.list{
    width: 90%;
    overflow-x: scroll;
}

.item {
    display: grid;
    grid-template-columns: 1fr 1fr 3fr 1fr;
    height: 2rem;
    min-width: 312px;
    margin-bottom: 1rem;
    background-color: #d6e0e1;
    border-bottom: 1px solid black;
    gap: 0.5rem;
    
}

@media (max-width: 760px) {
    .list{
        width: 100%;
    }

    .item{
        font-size: 10px;
        height: auto;
        padding: 0.5rem;
    }
}
</style>
