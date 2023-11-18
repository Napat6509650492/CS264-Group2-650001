import { createRouter, createWebHistory } from 'vue-router'
import { userInfoStore } from '../stores/userinfo';


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [{
      path: '/student',
      name: 'student',
      component: ()=> import('../views/StudentView.vue')
    },{
      path: '/login',
      name: 'login',
      component: ()=> import('../views/LoginView.vue')
    },
    {
      path: '/:pathMatch(.*)*',
      name: 'error',
      redirect:'login'
    }
  ]
})

router.beforeEach((to, from, next) => {
  const info = userInfoStore();
  
  
  
  if (to.name !== 'login') {
    var requestOptions = {
      method: 'GET',
      redirect: 'follow',
      credentials: 'include'
    };
    
    fetch("http://localhost:8080/api/auth/checkCookie", requestOptions)
    .then(response => {
      if(response.status == 200){
        response.json()
        .then(data=>{
          info.set(data)
          next();
        })
        .catch(err=>{
          alert('Plase Login');
          next({ name: 'login' });
        })
      }
    })

  } else {
    next();
  }
});

export default router
