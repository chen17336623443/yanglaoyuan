<template>
  <div class="outregistration">
    <div class="outnei">
      <el-row>
        <el-col style="font-size: 16px;font-family: 微软雅黑;color: lightskyblue">
          用户登记外出/返回
        </el-col>
        <el-col :span="12">
          123456
        </el-col>
        <el-col :span="12">
          123456
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
    export default {
        name: "Outregistration"
    }
</script>

<style>
  .outregistration{
    overflow: hidden;
    overflow-y: auto;
    -webkit-overflow-scrolling: touch;
    background: #dcdcdc36;
    height: 100%;
  }
 .outregistration .outnei{
   background: white;
   margin: 10px;
   padding: 10px;
   border-radius: 5px;
   height: 93%;
   box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
 }
</style>
