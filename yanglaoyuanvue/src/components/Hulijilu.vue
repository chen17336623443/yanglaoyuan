<template>
  <div class="div">
    <el-tabs type="border-card">
      老人名称:
      <el-select v-model="value" placeholder="请选择老人" @change="gaibian">
        <el-option
          v-for="item in options"
          :key="item.index"
          :label="item.tomName"
          :value="item.omId"
        >
        </el-option>
      </el-select>
      <el-button
        plain
        style="float: right"
        @click="(dialogFormVisible = true), (form = {}), (from1 = {})"
      >
        新增老人记录
      </el-button>
      <el-dialog
        title="新增老人单点护理消费记录"
        :visible.sync="dialogFormVisible"
      >
        <el-form ref="form" :model="form" label-width="70px" :inline="true">
          <el-form-item label="老人名称">
            <el-select v-model="form.omId" placeholder="请选择老人">
              <el-option
                v-for="item in options"
                :key="item.index"
                :label="item.tomName"
                :value="item.omId"
              >
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="项目选择">
            <el-select
              v-model="form.nuId"
              placeholder="请选择护理项目"
              @change="huli"
            >
              <el-option
                v-for="item in options1"
                :key="item.index"
                :label="item.nuName"
                :value="item.nuId"
              >
              </el-option>
            </el-select>
          </el-form-item>

          <fieldset style="padding: 20px">
            <legend>护理项目信息</legend>
            <el-form-item label="项目费用">
              <el-input v-model="from1.nuMonry" :readonly="true"></el-input>
            </el-form-item>

            <el-form-item label="收费类型">
              <el-input v-model="from1.nuWay" :readonly="true"></el-input>
            </el-form-item>

            <el-form-item label="所需时间">
              <el-input v-model="from1.nuTime" :readonly="true"></el-input>
            </el-form-item>
          </fieldset>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="submit"> 确 定 </el-button>
        </span>
      </el-dialog>
      <el-table
        :data="tableData"
        border
        show-summary
        style="width: 100%; margin-top: 20px"
      >
        <el-table-column prop="nrId" label="编号" width="180">
        </el-table-column>
        <el-table-column
          prop="oldmanByOmId.tomName"
          label="老人名称"
          width="180"
        >
        </el-table-column>
        <el-table-column
          prop="nursingByNuId.nuName"
          label="护理项目"
          width="180"
        >
        </el-table-column>
        <el-table-column
          prop="nrTime"
          label="护理时间"
          :formatter="formatDate"
          width="180"
        >
        </el-table-column>
        <el-table-column prop="nursingByNuId.nuWay" label="收费方式">
        </el-table-column>
        <el-table-column prop="nrMoney" sortable label="费用">
        </el-table-column>
      </el-table>
    </el-tabs>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [],
      dialogFormVisible: false,
      options: [],
      options1: [],
      from1: {},
      value: "",
      form: {},
    };
  },
  filters: {
    handleText(value) {
      if (!value) return "";
      if (value.length > 6) {
        return value.slice(0, 6) + "...";
      }
      return value;
    },
  },
  methods: {
    //页面加载查询护工数据
    loding() {
      this.$http.get("oldman/selectAll").then((res) => {
        this.options = res;
      });
      this.$http.get("nurc/allall").then((res) => {
        this.options1 = res;
      });
    },
    //护理
    huli() {
      this.$http.get("nurc/idall?nuid=" + this.form.nuId).then((res) => {
        this.from1 = res;
      });
    },
    //选择老人查看消费记录
    gaibian() {
      this.$http.get("nrc/byid?omid=" + this.value).then((res) => {
        this.tableData = res;
        this.$message({
          message: "已为你查询所有！",
          type: "success",
        });
      });
    },
    //新增消费记录确定
    submit() {
      if (
        this.form.omId == undefined ||
        this.form.nuId == undefined ||
        this.form.omId == "" ||
        this.form.nuId == ""
      ) {
        this.$message.error("请选择老人和项目！！！！");
      } else {
        this.$http
          .post("nrc/add/" + this.form.omId + "/" + this.form.nuId+"/"+this.from1.nuMonry)
          .then((res) => {
            this.$message({
              message: res,
              type: "success",
            });
            this.loding();
            this.dialogFormVisible=false
          });
      }
    },
    //修改时间格式
    formatDate(row, column) {
      // 获取单元格数据
      let data = row[column.property];

      let dt = new Date(data);
      return dt.getFullYear() + "-" + (dt.getMonth() + 1) + "-" + dt.getDate();
    },
  },
  created() {
    this.loding();
  },
};
</script>

<style scoped>
.div {
  padding: 20px;
  margin: 0;
}
</style>
