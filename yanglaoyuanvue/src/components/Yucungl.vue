<template>
    <div class="ycgl">
      <div style="background: white;padding: 10px;">
        <el-table
          ref="multipleTable"
          :data="deps"
          show-summary
          tooltip-effect="dark"
          style="width: 100%"
          @selection-change="handleSelectionChange">
          <el-table-column
            type="selection"
            width="70">
          </el-table-column>
          <el-table-column
            label="老人姓名">
            <template slot-scope="scope">
              {{scope.row.oldmanByOmId.tomName}}
            </template>
          </el-table-column>
          <el-table-column
            prop="oldmanByOmId.tomNumber"
            label="手机号">
          </el-table-column>
          <el-table-column
            prop="oldmanByOmId.tomCard"
            label="身份证号"
            width="240">
          </el-table-column>
          <el-table-column
            prop=""
            label="床位号">
          </el-table-column>
          <el-table-column
            prop="depMoney"
            label="账号存款余额">
          </el-table-column>
          <el-table-column
            prop="address"
            label="操作"
            show-overflow-tooltip>
          </el-table-column>
        </el-table>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          style="margin-top: 10px;"
          :current-page="current"
          :page-sizes="[1, 2, 3, 4 ,5]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
      </div>
    </div>
</template>

<script>
    export default {
      data() {
        return {
          tableData: [],
          multipleSelection: [],
          current:1,
          pageSize:5,
          total:0,
          http:'http://localhost:8089/',
          deps:[]
        }
      },
      methods: {
        getdep(){
          let param={
            no:this.current,
            size:this.pageSize
          };
          let ppp = this.$qs.stringify(param);
          this.$axios.post('deposit/pager',ppp)
            .then(r=>{
              console.log("dep:",r.list);
              this.deps = r.list;
              // this.total = r.data.total
            })
            .catch(e=>{

            })
        },
        toggleSelection(rows) {
          if (rows) {
            rows.forEach(row => {
              this.$refs.multipleTable.toggleRowSelection(row);
            });
          } else {
            this.$refs.multipleTable.clearSelection();
          }
        },
        handleSelectionChange(val) {
          this.multipleSelection = val;
        },
        andleCurrentChange(pagerindex){
          //参数是当前页码
          this.current = pagerindex;

        },
        /* pageSize 改变时会触发*/
        handleSizeChange(pagesize){
          this.pageSize=pagesize;

        }
      },
      mounted() {
        this.getdep();
      }
    }
</script>

<style>
  .ycgl{
    margin: 10px;
    padding: 10px;
    border-radius: 5px;
    background: whitesmoke;
    height: 530px;
  }
</style>
