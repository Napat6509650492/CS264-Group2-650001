import { defineStore } from 'pinia'

export const userInfoStore = defineStore('info', {
  state: () => ({
    isLogin:false,
    info:{
      "type": "",
      "username": "",
      "tu_status": "",
      "statusid": "",
      "displayname_th": "นภัส ไถ้บ้านกวย",
      "displayname_en": "",
      "email": "",
      "department": "",
      "faculty": ""
    }
}),
  
  getters: {
    getInfo: (state) => state.info,
    getLogin: (state) => state.isLogin
  },

  actions: {
    set(newInfo) {
      this.info = newInfo;
    },
    login(){
      this.isLogin = true;
    }
  },
})