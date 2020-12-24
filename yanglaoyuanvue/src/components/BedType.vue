<template>
  <div>
    <p></p>
    <!--修改BedType-->
    <el-dialog
      title="提示"
      :visible.sync="dialogVisiblexg"
      width="50%"
      :before-close="handleClose">
      <el-form :model="ruleFormup" :rules="rules" ref="ruleFormup" label-width="100px" class="demo-ruleForm">
        <el-row>
          <el-col :span="12">
            <el-form-item label="类型名称" prop="byname">
              <el-input v-model="ruleFormup.byname" style="width: 200px;"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="床位月价格" prop="bymoney">
              <el-input v-model="ruleFormup.bymoney" type="number" style="width: 200px;"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="包房月价格" prop="byallmoney">
              <el-input v-model="ruleFormup.byallmoney" type="number" style="width: 200px;"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="其他信息" prop="byinfo">
              <el-input v-model="ruleFormup.byinfo" type="textarea" style="width: 200px;"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item>
          <el-button type="primary" @click="submitFormxg('ruleFormup')">修改</el-button>
          <el-button @click="resetFormxg('ruleFormup')">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <!--新增BedType-->
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="50%"
      :before-close="handleClose">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-row>
          <el-col :span="12">
            <el-form-item label="类型名称" prop="byname">
              <el-input v-model="ruleForm.byname" style="width: 200px;"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="床位月价格" prop="bymoney">
              <el-input v-model="ruleForm.bymoney" type="number" style="width: 200px;"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="包房月价格" prop="byallmoney">
              <el-input v-model="ruleForm.byallmoney" type="number" style="width: 200px;"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="其他信息" prop="byinfo">
              <el-input v-model="ruleForm.byinfo" type="textarea" style="width: 200px;"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
          <el-button @click="resetForm('ruleForm')">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-button @click="addBedType" style="margin-left: 10px;margin-top: 5px"><i class="el-icon-circle-plus-outline" style="margin-right: 5px;"></i>新增</el-button>
    <el-button @click="open"> <i class="el-icon-remove-outline" style="margin-right: 5px"></i>删除</el-button>
    <el-table :data="tableData"tooltip-effect="dark"  @selection-change="handleSelectionChange">
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column prop="byname" label="类型名称" >
      </el-table-column>
       <el-table-column prop="bymoney" label="床位月价格">
      </el-table-column>
      <el-table-column prop="byallmoney" label="包房月价格">
      </el-table-column>
      <el-table-column prop="byinfo" label="其他信息">
      </el-table-column>
      <el-table-column prop="bytime" label="创建时间">
      </el-table-column>
      <el-table-column  label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="update(scope.row)"><i class="el-icon-edit" style="margin-right: 5px"></i>修改</el-button>
        </template >
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="current"
      :page-sizes="[10, 20, 30, 40]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      style="margin-top: 10px">
    </el-pagination>
  </div>
</template>

<script>
    export default {
        name: "BedType",
        data(){
          return{
            total:0,
            pageSize:10,
            current:1,

            multipleSelection:[],
            tableData:[],
            dialogVisible:false,
            ruleForm:{
              byname:'',
              bymoney:'',
              byallmoney:'',
              byinfo:'',
            },

            rules: {
              byname: [
                { required: true, message: '请输入名称', trigger: 'blur' }
              ],
              bymoney:[
                { required: true, message: '请输入价格', trigger: 'blur' }
              ]
            },

            dialogVisiblexg:false,
            ruleFormup:{
              byid:'',
              byname:'',
              bymoney:'',
              byallmoney:'',
              byinfo:'',
            },

          }
        },
        methods:{
          open() {
            if(this.multipleSelection.length==0){
              this.$message({
                type: 'info',
                message: '请选择要删除的对象'
              });
            }else{
              this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                let num = 0;
                for (let x of this.multipleSelection) {
                  num++;
                  console.log(x.byid)
                  let deldet = {
                    byid:x.byid
                  };
                  console.log(deldet);
                  let byid = this.$qs.stringify(deldet);
                  this.$axios.post("http://localhost:8089/bedType/delete",byid)
                  this.all();
                }
                if(num == this.multipleSelection.length){
                  console.log(num)
                  console.log(this.multipleSelection.length)
                  this.$message({
                    type: 'success',
                    message: '删除成功!'
                  });
                }

              }).catch(() => {
                this.$message({
                  type: 'info',
                  message: '已取消删除'
                });
              });
            }

          },
          /*修改*/
          update(v){
            console.log(v)
            this.dialogVisiblexg = true;
            this.ruleFormup = v;
          },
          /*form表单提交修改BedType*/
          submitFormxg(formName) {
            this.$refs[formName].validate((valid) => {
              if (valid) {
                console.log(this.ruleFormup);
                let update = {
                  byid:this.ruleFormup.byid,
                  byname:this.ruleFormup.byname,
                  bymoney:this.ruleFormup.bymoney,
                  byallmoney:this.ruleFormup.byallmoney,
                  byinfo:this.ruleFormup.byinfo,
                };
                console.log(update);
                let Bedtype = this.$qs.stringify(update);
                console.log(this.ruleForm)
                this.$axios.post("http://localhost:8089/bedType/update",Bedtype)
                  .then(r=>{
                    this.all();
                    this.dialogVisiblexg =false;
                  })
              } else {
                console.log('error submit!!');
                return false;
              }
            });
          },
          /*关闭 修改BedType的对话*/
          resetFormxg(formName) {
            this.$refs[formName].resetFields();
            this.dialogVisiblexg =false;
          },

          /*打开 新增BedType的对话*/
          addBedType(){
            this.ruleForm={
                byname:'',
                bymoney:'',
                byallmoney:'',
                byinfo:'',
            },
            this.dialogVisible = true;
          },
          /*form表单提交新增BedType*/
          submitForm(formName) {
            this.$refs[formName].validate((valid) => {
              if (valid) {
                let inserts = {
                  byname:this.ruleForm.byname,
                  bymoney:this.ruleForm.bymoney,
                  byallmoney:this.ruleForm.byallmoney,
                  byinfo:this.ruleForm.byinfo,
                };
                console.log(inserts);
                let Bedtype = this.$qs.stringify(inserts);
                console.log(this.ruleForm)
                this.$axios.post("http://localhost:8089/bedType/insert",Bedtype)
                  .then(r=>{
                    this.all();
                    this.dialogVisible =false;
                  })
              } else {
                console.log('error submit!!');
                return false;
              }
            });
          },
          /*关闭 新增BedType的对话*/
          resetForm(formName) {
            this.$refs[formName].resetFields();
            this.dialogVisible =false;
          },
          /*询问是否关闭对话*/
          handleClose(done) {
            this.$confirm('确认关闭？')
              .then(_ => {
                done();
              })
              .catch(_ => {});
          },
          /*多选删除获取选择中的数据*/
          handleSelectionChange(val) {
            this.multipleSelection = val;
            console.log(val)
          },
          /*查询Bedtype的所有数据*/
          all2(){
            this.$http.post("http://localhost:8089/bedType/all",this.$qs.stringify({
              no:this.current,
              size:this.pageSize
            }) )
              .then((res) => {
                this.tableData = res.list;
                this.total=res.total;
              })
          },
          all(){
            this.current =1 ;
            this.$http.post("http://localhost:8089/bedType/all",this.$qs.stringify({
              no:this.current,
              size:this.pageSize
            }) )
              .then((res) => {
                this.tableData = res.list;
                console.log(res);
                this.total=res.total;
              })
          },
          /* pageSize 改变时会触发*/
          handleSizeChange(pagesize){
            this.pageSize=pagesize;
            this.all2();
          },
          /*分页查询底部*/
          handleCurrentChange(pagerindex){
            //参数是当前页码
            this.current = pagerindex;
            this.all2();
          },
        },
        created() {
          this.all2();
        }
    }
</script>

<style scoped>

</style>
