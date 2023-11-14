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

  if (to.name !== 'login' && !info.getLogin) {
    next({ name: 'login' });
  } else {
    next();
  }
});

export default router
