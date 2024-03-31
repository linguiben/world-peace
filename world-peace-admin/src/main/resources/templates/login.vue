<template>
  <div>
    <div class="logo">
      <div class="w">欢迎登录</div>
    </div>
    <div class="login-wrap">
      <div class="w">
        <div class="login-form">
          <div class="login-tab login-tab-r">账户登录</div>
          <div class="login-box">
            <div class="msg-wrap" v-show="formMsg">
              <div class="msg-error"><b></b>{{ formMsg }}</div>
            </div>
            <div class="mc">
              <div class="form">
                <m-slidercaptcah
                    class="captcha-wrap"
                    v-model:value="slider"
                    @success="onSliderSuccess"
                />

                <form id="formlogin" onsubmit="return false;">
                  <div class="item item-fore1">
                    <label
                        for="loginname"
                        class="login-label name-label"
                    ></label>
                    <input
                        v-model="form.username"
                        type="text"
                        class="itxt"
                        tabindex="1"
                        autocomplete="off"
                        placeholder="邮箱/用户名/登录手机"
                    />
                  </div>
                  <div
                      id="entry"
                      class="item item-fore2"
                      style="visibility: visible"
                  >
                    <label
                        class="login-label pwd-label"
                        for="nloginpwd"
                    ></label>
                    <input
                        v-model="form.password"
                        type="password"
                        name="nloginpwd"
                        class="itxt itxt-error"
                        tabindex="2"
                        autocomplete="off"
                        placeholder="密码"
                    />
                  </div>
                  <div class="item-fore5">
                    <div class="login-btn" @click="submit">
                      <a
                          href="javascript:;"
                          class="btn-img btn-entry"
                          id="loginsubmit"
                          tabindex="6"
                      >登&nbsp;&nbsp;&nbsp;&nbsp;录</a
                      >
                    </div>
                    <div class="regist">
                      <a href="/regist">立即注册</a>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="login-banner">
        <div class="w">
          <div id="banner-bg" class="i-inner"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import { login } from '@/api/user'
import { useStore } from 'vuex'
import { reactive, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import mSlidercaptcah from '../../components/m-slidercaptcah'
export default {
  components: { mSlidercaptcah },
  setup () {

    const route = useRoute()
    const router = useRouter()
    const store = useStore()

    const slider = ref(false)
    const formMsg = ref('')

    const form = reactive({
      username: '',
      password: ''
    })


    const submit = () => {
      if (slider.value) return
      if (!form.username && !form.password) {
        formMsg.value = '请输入账户名和密码'
        return
      }
      if (!form.username) {
        formMsg.value = '请输入账户名'
        return
      }
      if (!form.password) {
        formMsg.value = '请输入密码'
        return
      }
      formMsg.value = ''
      slider.value = true
    }

    const onSliderSuccess = tracks => {
      login({
        ...form,
        tracks
      }).then(async ({ token }) => {
        localStorage.setItem('token', token)
        await store.dispatch('getUserInfo')
        router.replace({
          path: route.query.redirect_url || '/'
        })
      }).catch(() => {
        slider.value = false
      })
    }

    return {
      slider,
      formMsg,
      form,
      submit,
      onSliderSuccess
    }
  }
}
</script>



<style lang="scss" scoped>
* {
  box-sizing: content-box;
}
.logo {
  height: 110px;
  background: #fff;
  line-height: 110px;
  font-size: 24px;
}
.login-wrap {
  position: relative;
  height: 475px;
  z-index: 5;
  .login-banner {
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 475px;
    background: #e93854;
  }
  .i-inner {
    position: relative;
    z-index: 3;
    height: 475px;
    background: url(~@/assets/img/5aa68632Nd7790d0c.png) 0px 0px no-repeat;
    background-color: #e93854;
  }
}

.login-form {
  float: right;
  top: 62px;
  position: relative;
  z-index: 4;
  background: #fff;
  overflow: hidden;
  width: 346px;
  .login-tab {
    height: 54px;
    line-height: 54px;
    font-size: 18px;
    font-family: 'microsoft yahei';
    text-align: center;
    border-bottom: 1px solid #f4f4f4;
    background: #fff;
    display: block;
    font-weight: 700;
    color: #e4393c;
    width: 346px;
  }
}
.form .item-fore1 {
  z-index: 6;
}
.form .item-fore1,
.form .item-fore2 {
  border: 1px solid #bdbdbd;
  height: 38px;
  width: 304px;
}
.form .item {
  position: relative;
  margin-bottom: 20px;
  z-index: 1;
  box-sizing: content-box;
}
.regist {
  text-align: center;
  margin-top: 20px;
  /deep/ a {
    color: #b61d1d;
    font-size: 14px;
  }
}
.form .item .login-label {
  position: absolute;
  z-index: 3;
  top: 0;
  left: 0;
  width: 38px;
  height: 38px;
  border-right: 1px solid #bdbdbd;
  background: url('~@/assets/img/pwd-icons-new.png') no-repeat;
}
.form .item .name-label {
  background-position: 0 0;
}
.form .item .pwd-label {
  background-position: -48px 0;
}
.form .itxt {
  line-height: 18px;
  height: 18px;
  border: 0;
  padding: 10px 0 10px 50px;
  width: 254px;
  float: none;
  overflow: hidden;
  font-size: 14px;
  font-family: '\5b8b\4f53';
  outline: none;
  box-sizing: content-box;
}
.login-box {
  position: relative;
  padding: 40px 20px;
}
.login-btn {
  border: 1px solid #cb2a2d;
  margin-top: 40px;
  height: 32px;
  width: 99%;
  position: relative;
  text-align: center;
  .btn-img {
    border: 1px solid #e85356;
    display: block;
    width: 302px;
    background: #e4393c;
    height: 31px;
    line-height: 31px;
    color: #fff;
    font-size: 20px;
    font-family: 'Microsoft YaHei';
    outline: none;
  }
}
.msg-wrap {
  position: absolute;
  left: 20px;
  right: 20px;
  z-index: 99;
  top: 9px;
}
.login-form .msg-error {
  position: relative;
  background: #ffebeb;
  color: #e4393c;
  border: 1px solid #faccc6;
  padding: 3px 10px 3px 40px;
  line-height: 15px;
  height: auto;
  b {
    position: absolute;
    top: 50%;
    left: 10px;
    display: block;
    margin-top: -8px;
    width: 16px;
    height: 16px;
    overflow: hidden;
    background: url(~@/assets/img/pwd-icons-new.png) -104px -49px no-repeat;
  }
}
.captcha-wrap {
  position: absolute;
  background-color: #fff;
  z-index: 99;
  box-shadow: 0 0 2px 2px #eee;
  border: 1px solid #eee;
  width: 304px;
  bottom: 118px;
  padding: 12px 18px;
  box-sizing: border-box;
}
</style>
