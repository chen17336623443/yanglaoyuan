<template>
  <div class="homepage-hero-module">
    <div class="video-container">
      <div>
       <img src="../../static/imgs/beijin.jpg" alt="" style="width:100%"/>
      </div>
      <div class="login-form">
        <h2>智慧养老登录系统</h2>
        <div class="form-input">
          <input
            type="text"
            v-model="loginForm.ename"
            placeholder="user"
            style="padding: 10px"
          />
        </div>
        <div class="form-input">
          <input
            type="password"
            v-model="loginForm.elpass"
            @keyup.enter="login"
            placeholder="password"
            style="padding: 10px"
          />
        </div>
        <div class="form-input">
          <input type="submit" @click="login()" value="立即登录" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Video",
  data() {
    return {
      loginForm: {
        ename: "",
        elpass: "",
      },
      vedioCanPlay: false,
      fixStyle: "",
    };
  },
  methods: {
    login() {
      if (this.loginForm.ename === "" || this.loginForm.elpass === "") {
        this.$message.error("账号或密码不能为空");
      } else {
        this.$http
          .post("user/login", {
            uphone: this.loginForm.ename,
            upass: this.loginForm.elpass,
          })
          .then((res) => {
            console.log(res);
            localStorage.setItem("token", res.object.token);
            localStorage.setItem("uid", res.object.user.uid);
            localStorage.setItem("uname", res.object.user.uname);
            this.$router.push("/home");
          })
          .catch((error) => {
            this.$message.error("账号或密码错误");
            console.log(error);
          });
      }
    },
  },
  mounted: function () {
    //屏幕自适应
    window.onresize = () => {
      const windowWidth = document.body.clientWidth;
      const windowHeight = document.body.clientHeight;
      const windowAspectRatio = windowHeight / windowWidth;
      let videoWidth;
      let videoHeight;
      if (windowAspectRatio < 0.5625) {
        videoWidth = windowWidth;
        videoHeight = videoWidth * 0.5625;
        this.fixStyle = {
          height: windowWidth * 0.5625 + "px",
          width: windowWidth + "px",
          "margin-bottom": (windowHeight - videoHeight) / 2 + "px",
          "margin-left": "initial",
        };
      } else {
        videoHeight = windowHeight;
        videoWidth = videoHeight / 0.5625;
        this.fixStyle = {
          height: windowHeight + "px",
          width: windowHeight / 0.5625 + "px",
          "margin-left": (windowWidth - videoWidth) / 2 + "px",
          "margin-bottom": "initial",
        };
      }
    };
    window.onresize();
  },
};
</script>
<style scoped>

.login-form2 {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 400px;
  padding: 60px 40px;
  background-color: rgba(255, 255, 255, 0.4);
  box-shadow: 0 15px 20px rgba(0, 0, 0, 0.2);
}
.login-form {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 400px;
  padding: 60px 40px;
  background-color: rgba(255, 255, 255, 0.4);
  box-shadow: 0 15px 20px rgba(0, 0, 0, 0.2);
}
.login-form h2 {
  margin: 0 0 40px;
  padding: 0;
  text-align: center;
}
.login-form .form-input {
  position: relative;
  margin: 20px 0;
}
.login-form .form-input input {
  outline: none;
  height: 40px;
  box-sizing: border-box;
  width: 100%;
  line-height: -20px;
  border: 1px solid rgba(0, 0, 0, 0.5);
  background: transparent;
  font-size: 18px;
}
.login-form .form-input input[type="submit"] {
  background-color: #0090ff;
  color: #fff;
  border: none;
  cursor: pointer;
}
.login-form a {
  display: block;
  margin-top: 30px;
  color: #000;
  font-weight: 700;
}

.homepage-hero-module,
.video-container {
  position: relative;
  height: 100vh;
  overflow: hidden;
}

.video-container .poster img {
  z-index: 0;
  position: absolute;
}

.video-container .filter {
  z-index: 1;
  position: absolute;
  background: rgba(0, 0, 0, 0.4);
  width: 100%;
}

.fillWidth {
  width: 100%;
}
</style>
