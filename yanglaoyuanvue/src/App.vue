<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: "App",
  data(){
    return{
      activeIndex2:""
    }
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
  },
};
</script>

<style>
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
  color: #c0ccda;
}
.el-menu{
  border-right:0px;}
.zi {
  padding-left: 60px;
}
</style>
