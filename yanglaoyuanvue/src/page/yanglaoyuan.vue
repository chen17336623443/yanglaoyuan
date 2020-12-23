<template>
  <div>
    <el-container id="myc">
      <el-scrollbar style="background-color:#84A9B7">
        <el-aside width="222px">
          <el-menu
            default-active="5"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            background-color="#84A9B7"
            text-color="#fff"
            active-text-color="#ffd04b"
          >
            <router-link to="/home" style="text-decoration: none">
              <el-menu-item index="1" style="height: 60px">
                <img src="../../static/imgs/ylLogo.png" />
                <span style="font-size: 20px; color: white">智慧养老系统</span>
              </el-menu-item>
            </router-link>
            <!--表示可展开的菜单-->
            <el-submenu index="2">
              <template slot="title">
                <i class="el-icon-bell"></i>
                <span>接待管理</span>
              </template>
              <router-link to="" style="text-decoration: none">
                <el-menu-item index="2-1">咨询登记</el-menu-item>
              </router-link>
              <router-link to="" style="text-decoration: none">
                <el-menu-item index="2-2">预约登记</el-menu-item>
              </router-link>
              <router-link to="" style="text-decoration: none">
                <el-menu-item index="2-3">试住登记</el-menu-item>
              </router-link>
              <router-link to="" style="text-decoration: none">
                <el-menu-item index="2-4">入住登记</el-menu-item>
              </router-link>
              <router-link to="" style="text-decoration: none">
                <el-menu-item index="2-5">退住详情</el-menu-item>
              </router-link>
            </el-submenu>

            <el-submenu index="3">
              <template slot="title">
                <i class="el-icon-user"></i>
                <span>老人管理</span>
              </template>
              <router-link tag="span" to="/outregistration" style="text-decoration: none">
                <el-menu-item index="3-1">外出登记</el-menu-item>
              </router-link>
              <router-link to="" style="text-decoration: none">
                <el-menu-item index="3-2">探视来访</el-menu-item>
              </router-link>
              <router-link to="" style="text-decoration: none">
                <el-menu-item index="3-3">意外记录</el-menu-item>
              </router-link>
              <router-link to="" style="text-decoration: none">
                <el-menu-item index="3-4">退住登记</el-menu-item>
              </router-link>
              <router-link to="" style="text-decoration: none">
                <el-menu-item index="3-5">退住查询</el-menu-item>
              </router-link>
            </el-submenu>

            <el-submenu index="4">
              <template slot="title">
                <i class="el-icon-moon"></i>
                <span>床位管理</span>
              </template>
              <router-link to="/Floor" style="text-decoration: none">
                <el-menu-item index="4-1">楼房管理</el-menu-item>
              </router-link>
              <router-link to="/BedType" style="text-decoration: none">
                <el-menu-item index="4-2">床位类型管理</el-menu-item>
              </router-link>
              <router-link to="" style="text-decoration: none">
                <el-menu-item index="4-3">机构信息维护</el-menu-item>
              </router-link>
            </el-submenu>

            <el-submenu index="5">
              <template slot="title">
                <i class="el-icon-scissors"></i>
                <span>护理管理</span>
              </template>
              <router-link to="/hugongguanli" style="text-decoration: none">
                <el-menu-item index="5-1">护工管理</el-menu-item>
              </router-link>
              <router-link to="Huliguanli" style="text-decoration: none">
                <el-menu-item index="5-2">护理项目设置</el-menu-item>
              </router-link>
              <router-link to="" style="text-decoration: none">
                <el-menu-item index="5-3">护理级别设置</el-menu-item>
              </router-link>
              <router-link to="" style="text-decoration: none">
                <el-menu-item index="5-4">护理记录</el-menu-item>
              </router-link>
            </el-submenu>

            <el-submenu index="6">
              <template slot="title">
                <i class="el-icon-dish-1"></i>
                <span>餐饮管理</span>
              </template>
              <router-link to="foodManage" style="text-decoration: none">
                <el-menu-item index="6-1">食物管理</el-menu-item>
              </router-link>
              <router-link to="foodPackge" style="text-decoration: none">
                <el-menu-item index="6-2">食谱管理</el-menu-item>
              </router-link>
              <router-link to="" style="text-decoration: none">
                <el-menu-item index="6-3">点餐管理</el-menu-item>
              </router-link>
              <router-link to="" style="text-decoration: none">
                <el-menu-item index="6-4">送餐记录</el-menu-item>
              </router-link>
            </el-submenu>

            <el-submenu index="7">
              <template slot="title">
                <i class="el-icon-bank-card"></i>
                <span>费用管理</span>
              </template>
              <router-link tag="span" to="/ycgl" style="text-decoration: none">
                <el-menu-item index="7-1">预存管理</el-menu-item>
              </router-link>
              <router-link to="" style="text-decoration: none">
                <el-menu-item index="7-2">入住缴费</el-menu-item>
              </router-link>
              <router-link to="" style="text-decoration: none">
                <el-menu-item index="7-3">缴费登记</el-menu-item>
              </router-link>
              <router-link to="" style="text-decoration: none">
                <el-menu-item index="7-4">欠费管理</el-menu-item>
              </router-link>
              <router-link tag="span" to="/fyls" style="text-decoration: none">
                <el-menu-item index="7-5">费用流水</el-menu-item>
              </router-link>
            </el-submenu>
          </el-menu>
        </el-aside>
      </el-scrollbar>
      <el-container>
        <el-header style="padding:0px">
          <el-menu
            :default-active="activeIndex2"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
            background-color="#A8CCDC"
            text-color="#fff"
            active-text-color="#ffd04b"
          >
            <el-submenu index="8" style="float: right">
              <template slot="title">
                <i class="el-icon-s-custom"></i>
                {{ user }}
              </template>
              <el-menu-item index="8-1" @click="tuichu">退出</el-menu-item>
            </el-submenu>
          </el-menu>
        </el-header>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "App",
  data() {
    return {
      user: "",
      activeIndex2: '1'
    };
  },
  provide() {
    return {
      say: this.loding,
      tc: this.tuichu,
    };
  },
  methods: {
    handleOpen(key, keyPath) {},
    handleClose(key, keyPath) {},
    handleSelect(key, keyPath) {},
    tuichu() {
      this.$confirm("是否退出当前登录帐号！", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$axios
            .get(
              "http://localhost:8089/user/deltoken?ename=" +
                localStorage.getItem("uname")
            )
            .then((res) => {
              this.$message({
                type: "success",
                message: "退出成功",
              });
              localStorage.clear();
              this.$router.push("/login");
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消",
          });
        });
    },
  },
  mounted() {},
  beforeDestroy() {
    clearTimeout(this.timer);
  },
  created() {
    this.user = localStorage.getItem("uname");
    this.$notify({
      title: "登录成功",
      message:
        "亲爱的:" + localStorage.getItem("uname") + ",欢迎登录智慧养老管理系统",
      type: "success",
    });
  },
};
</script>

<style scoped>
* {
  padding: 0;
  margin: 0;
}
/*在顶级el-container上面加上这段代码，会自动撑满整个页面*/
#myc {
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
}
.el-header {
  background-color: #5c5464;
}
.el-aside {
  background-color: #5c5464;
}
.el-footer {
  background-color: #000000;
  color: white;
}
/*修改图标颜色*/
.el-submenu__title i {
  color: #fff;
}
.el-menu {
  border-right: 0px;
}
.ulli1 {
  list-style: none;
}
.ulli1 li {
  line-height: 30px;
  height: 30px;
}
.ulli1 li span {
  color: #93c9d3;
}
.el-scrollbar__wrap {
  overflow-x: hidden;
}
.el-select-dropdown .el-scrollbar .el-scrollbar__wrap {
  overflow: scroll !important;
}
</style>
