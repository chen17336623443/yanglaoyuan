<template>
  <div class="outregistration">
    <div>
      <el-row style="background: white;border-radius: 5px;margin: 10px;padding: 10px">
        <el-col style="font-family: 微软雅黑;">
          <el-tag type="success" style="font-size: 14px;">用户登记外出/返回</el-tag>
        </el-col>
      </el-row>
    </div>
    <div class="outnei">
      <el-row style="box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);padding: 10px;border-radius: 5px">
        <el-col :span="6">
          <span style="font-size: 14px">老人姓名：</span>
          <el-autocomplete
            size="small"
            v-model="state"
            style="width: 60%"
            :fetch-suggestions="querySearchAsync"
            placeholder="请输入姓名"
            @select="handleSelect"
          ></el-autocomplete>
        </el-col>
        <el-col :span="6">
          <span style="font-size: 14px">记录类型：</span>
          <el-select v-model="selectOutregistration.outtrue" clearable placeholder="请选择" size="small" style="width: 60%">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="6">
          <span style="font-size: 14px">老人姓名：</span><el-input size="small" style="width: 60%"></el-input>
        </el-col>
        <el-col :span="6">
          <span style="font-size: 14px">老人姓名：</span><el-input size="small" style="width: 60%"></el-input>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
    export default {
      name: "Outregistration",
      data(){
        return{
          restaurants: [],
          state: '',
          timeout:  null,
          selectOutregistration:{
            tomName:'',
            outtrue:'',
          },
          options:[{
              value: '选项1',
              label: '黄金糕'
            }, {
              value: '选项2',
              label: '双皮奶'
            }, {
              value: '选项3',
              label: '蚵仔煎'
            }, {
              value: '选项4',
              label: '龙须面'
            }, {
              value: '选项5',
              label: '北京烤鸭'
            }]
        }
      },
      methods:{
        selectOldmanAll(){
          let mm=[];
          this.$axios.post("oldman/selectAll")
          .then(r=>{
            r.forEach(item=>{
              let ss={"value":item.tomName,"address":item.omId};
              mm.push(ss);
            })
          })
          return mm;
        },
        querySearchAsync(queryString, cb) {
          var restaurants = this.restaurants;
          var results = queryString ? restaurants.filter(this.createStateFilter(queryString)) : restaurants;
          clearTimeout(this.timeout);
          this.timeout = setTimeout(() => {
            cb(results);
          }, 3000 * Math.random());
        },
        createStateFilter(queryString) {
          return (state) => {
            return (state.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
          };
        },
        handleSelect(item) {
          console.log(item);
        }
      },
      created() {
        this.restaurants=this.selectOldmanAll();
      }
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
   height: 82%;
   box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
 }
</style>
