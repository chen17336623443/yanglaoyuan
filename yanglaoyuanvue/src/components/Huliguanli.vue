<template>
  <div class="div">
    <el-tabs type="border-card">
      <el-tab-pane label="护理信息">
        <el-row>
          <el-col :span="6" style="margin-left: 15px">
            <el-card class="box-card" style="height: 590px">
              <div slot="header" class="clearfix">
                <span>护理项目类别</span>
                <el-button style="float: right; padding: 3px 0" type="text">
                  新增类别
                </el-button>
              </div>
              <ul style="list-style: none">
                <li class="li1" @click="leibei">
                  <span>aaa</span>
                </li>
              </ul>
            </el-card>
          </el-col>
          <el-col :span="17" style="margin-left: 15px">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>基本信息</span>
                <el-button
                  style="float: right; padding: 3px 0"
                  type="text"
                  v-show="form != {} ? false : true"
                >
                  修改
                </el-button>
              </div>
              <el-form ref="form" :model="form" label-width="80px">
                <el-form-item label="类别名称">
                  <el-input
                    v-model="form.name"
                    style="width: 250px"
                    :readonly="true"
                  ></el-input>
                </el-form-item>
                <el-form-item label="类别备注">
                  <el-input
                    type="textarea"
                    v-model="form.desc"
                    rows="3"
                    :readonly="true"
                    resize="none"
                  ></el-input>
                </el-form-item>
              </el-form>
            </el-card>
            <el-card class="box-card" style="margin-top: 5px">
              <div slot="header" class="clearfix">
                <span>项目列表</span>
                <el-button-group style="float: right; margin-top: -5px">
                  <el-button size="small" plain>新增护理</el-button>
                  <el-button size="small" plain>修改护理</el-button>
                </el-button-group>
              </div>
              <el-table
                :data="tableData"
                size="mini"
                style="width: 100%"
                border
              >
                <el-table-column prop="date" label="日期" width="180">
                </el-table-column>
                <el-table-column prop="name" label="姓名" width="180">
                </el-table-column>
                <el-table-column prop="address" label="地址"> </el-table-column>
              </el-table>
            </el-card>
          </el-col>
        </el-row>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {},
      tableData: [],
    };
  },
  methods: {
      loding(){

      },
      leibei(){

      }
  },
  created() {},
};
</script>

<style scoped>
.div {
  margin: 0;
  padding: 20px;
}
.li1 {
  color: #909399;
}

.li1:hover {
  color: #409eff;
  cursor: pointer;
}
</style>