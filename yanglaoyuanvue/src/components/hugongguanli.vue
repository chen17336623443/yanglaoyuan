<template>
  <div class="div">
    <el-tabs type="border-card">
      <el-tab-pane label="护工信息">
        <el-form ref="form" :model="form" label-width="70px" :inline="true">
          <el-form-item label="护工名称">
            <el-input
              v-model="form.name"
              style="width: 140px"
              placeholder="请输入护工名称"
            ></el-input>
          </el-form-item>
          <el-form-item label="联系电话">
            <el-input
              v-model="form.phone"
              style="width: 140px"
              placeholder="请输入联系电话"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button icon="el-icon-search" circle @click="like"></el-button>
          </el-form-item>
          <el-form-item style="margin-left: 300px">
            <el-button type="primary" round>修改护工</el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="danger" round>离职</el-button>
          </el-form-item>
        </el-form>
        <el-table
          ref="multipleTable"
          :data="tableData"
          tooltip-effect="dark"
          style="width: 100%"
          @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection" width="55"> </el-table-column>
          <el-table-column prop="acId" label="护工编号" width="100">
          </el-table-column>
          <el-table-column prop="acName" label="护工姓名" width="120">
          </el-table-column>
          <el-table-column prop="acIdcare" label="身份证号" width="180">
          </el-table-column>
          <el-table-column prop="acPhone" label="联系方式" width="120">
          </el-table-column>
          <el-table-column label="性别" width="80">
            <template slot-scope="s">
              <span v-if="s.row.acSex == 0">女</span>
              <span v-if="s.row.acSex == 1">男</span>
            </template>
          </el-table-column>
          <el-table-column label="地址" width="140">
            <template slot-scope="s">
              <el-popover trigger="hover" placement="top">
                <p>{{ s.row.acDizi }}</p>
                <div slot="reference" class="name-wrapper">
                  {{ s.row.acDizi | handleText }}
                </div>
              </el-popover>
            </template>
          </el-table-column>
          <el-table-column label="看护老人" width="120">
            <template slot-scope="s">
              <el-popover placement="right" width="400" trigger="click">
                <el-table :data="olddata">
                  <el-table-column
                    width="130"
                    property="tomName"
                    label="老人名称"
                  ></el-table-column>
                  <el-table-column
                    width="130"
                    property="tomSex"
                    label="老人性别"
                  ></el-table-column>
                  <el-table-column
                    width="130"
                    property="tomNumber"
                    label="联系电话"
                  ></el-table-column>
                </el-table>
                <el-button
                  type="text"
                  @click="chakan(s.row.acId)"
                  slot="reference"
                >
                  {{ s.row.oldmenByAcId.length }}
                </el-button>
              </el-popover>
            </template>
          </el-table-column>
          <el-table-column prop="address" label="操作" show-overflow-tooltip>
            <template slot-scope="s">
              <el-button type="text" @click="update(s.row.acId)"
                >修改</el-button
              >
              <el-button type="text" @click="update(s.row.acId)"
                >离职</el-button
              >
            </template>
          </el-table-column>
        </el-table>

        <el-pagination
          style="float: right; margin-top: 20px"
          @current-change="handleCurrentChange"
          :page-size="pagesize"
          layout="prev, pager, next, jumper"
          :total="total"
        >
        </el-pagination>
      </el-tab-pane>
      <el-tab-pane label="护工新增">
        <div style="width: 1000px">
          <el-form ref="form" :model="form" label-width="80px" :inline="true">
            <el-form-item label="护工名称">
              <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="身份证号">
              <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="联系电话">
              <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="出生年月">
              <el-date-picker
                v-model="form.date"
                type="date"
                placeholder="选择日期"
                style="width: 202px"
              >
              </el-date-picker>
            </el-form-item>
            <el-form-item label="性别">
              <el-radio v-model="form.sex" label="0">女</el-radio>
              <el-radio v-model="form.sex" label="1">男</el-radio>
            </el-form-item>
            <br/>
            <el-form-item label="家庭地址">
              <el-input v-model="form.name"></el-input>
            </el-form-item>
            <br/>
            <el-form-item label="详情地址">
              <el-input v-model="form.name"></el-input>
            </el-form-item>
          </el-form>
        </div>
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
      pagesize: 0,
      pagenum: 0,
      olddata: [],
      total: 0,
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
      this.$http.get("aca/all?num=1").then((res) => {
        this.pagenum = 1;
        this.tableData = res.list;
        this.pagesize = res.pageSize;
        this.total = res.total;
      });
    },
    //根据护工id查看老人
    chakan(id) {
      this.olddata = [];
      this.$http.get("aca/byid?acid=" + id).then((res) => {
        this.olddata = res;
      });
    },
    //分页上下页跳转
    handleCurrentChange() {
      if (this.pagenum == 1) {
        this.$http.get("aca/all?num=" + val).then((res) => {
          this.pagenum = 1;
          this.tableData = res.list;
          this.pagesize = res.pageSize;
          this.total = res.total;
        });
      }
    },
    //复选框获取选中行数据
    handleSelectionChange(val) {
      console.log(val);
    },
    //根据名称和电话模糊查询
    like() {
      if (
        (this.form.name == undefined && this.form.phone == undefined) ||
        (this.form.name == "" && this.form.phone == "") ||
        (this.form.name == "" && this.form.phone == undefined) ||
        (this.form.name == undefined && this.form.phone == "")
      ) {
        this.loding();
      } else {
        this.$http
          .get(
            "aca/like?num=1&acname=" +
              this.form.name +
              "&acphone=" +
              this.form.phone
          )
          .then((res) => {
            this.pagenum = 2;
            this.tableData = res.list;
            this.pagesize = res.pageSize;
            this.total = res.total;
          });
      }
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
.el-table td,
.el-table th {
  text-align: center;
}
</style>
