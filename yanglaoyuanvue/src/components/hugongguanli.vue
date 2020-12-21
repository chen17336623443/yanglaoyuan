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
            <el-button icon="el-icon-search" circle></el-button>
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
          <el-table-column prop="acId" label="护工编号" width="80">
          </el-table-column>
          <el-table-column prop="acName" label="护工姓名" width="120">
          </el-table-column>
          <el-table-column prop="acIdcare" label="身份证号" width="180">
          </el-table-column>
          <el-table-column prop="acPhone" label="联系方式" width="120">
          </el-table-column>
          <el-table-column label="性别" width="60">
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
              <el-button type="text" @click="update(s.row.acId)">
                {{ s.row.oldmenByAcId.length }}
              </el-button>
            </template>
          </el-table-column>
          <el-table-column prop="address" label="操作" show-overflow-tooltip>
            <template slot-scope="s">
              <el-button type="text" @click="update(s.row.acId)">修改</el-button>
              <el-button type="text" @click="update(s.row.acId)">离职</el-button>
            </template>
          </el-table-column>
        </el-table>

        <el-pagination
          style="float: right"
          @current-change="handleCurrentChange"
          :page-size="pagesize"
          layout="prev, pager, next, jumper"
          :total="total"
        >
        </el-pagination>
      </el-tab-pane>
      <el-tab-pane label="护工新增"> </el-tab-pane>
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
      total: 0,
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
    loding() {
      this.$http.get("aca/all?num=1").then((res) => {
        console.log(res);
        this.pagenum = 1;
        this.tableData = res.list;
        this.pagesize = res.pageSize;
        this.total = res.total;
      });
    },
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
    handleSelectionChange(val) {
      console.log(val);
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
