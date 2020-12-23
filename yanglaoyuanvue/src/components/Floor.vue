<template>
  <div>
    <el-container style="height: 500px; border: 1px solid #eee">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-tree :data="floortree" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
      </el-aside>
      <!--新增楼栋-->
      <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="50%"
        :before-close="handleClose">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-row>
            <el-col :span="12">
              <el-form-item label="楼栋名称" prop="fname">
               <el-input v-model="ruleForm.fname" style="width: 200px;"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="楼层数量" prop="lcnumber">
                <el-input v-model="ruleForm.lcnumber" type="number" style="width: 200px;"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="楼层名称格式" prop="lcstyle">
                <el-select v-model="ruleForm.lcstyle"  style="width: 200px">
                  <el-option label="第N层" value="1"></el-option>
                  <el-option label="楼名N层" value="2"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="房间数量" prop="fjnumber">
                <el-input v-model="ruleForm.fjnumber" type="number" style="width: 200px"></el-input>/层
              </el-form-item>
            </el-col>
          </el-row>


          <el-row>
            <el-col :span="7">
              <el-form-item label="房间名称前缀" prop="mcqz">
                <el-input v-model="ruleForm.mcqz"  style="width: 100px"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="房间名称格式" >
                <el-input  :disabled="true"  style="width: 200px" value="几零几(XYZ)">
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="7">
              <el-form-item label="房间名称后缀" prop="mchz">
                <el-input v-model="ruleForm.mchz"  style="width: 100px"></el-input>
              </el-form-item>

            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="床位数量" prop="cwnumber">
                <el-input  style="width: 200px" v-model="ruleForm.cwnumber">
                </el-input>/房间
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="床位类型" prop="cwtype">
                <el-select v-model="ruleForm.cwtype" filterable placeholder="请选择" >
                  <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                    <span style="float: left">{{ item.label }}</span>
                    <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value}}</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
            <el-button @click="resetForm('ruleForm')">取消</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
      <el-container  v-show="stepthree">
        <el-header style="text-align: left; font-size: 12px">
          <el-button @click="addloudong"> <i class="el-icon-circle-plus-outline" style="margin-right: 5px"></i>新增楼栋</el-button>
        </el-header>
        <el-main>
          <el-table :data="tableData">
            <el-table-column prop="fname" label="楼栋名称">
            </el-table-column>
            <el-table-column prop="floorsByFid.length" label="楼层数量">
            </el-table-column>
            <el-table-column  label="房间数量">
              <template slot-scope="scope">
                <!--display: none隐藏无关数据 然后根据length给numberhome进行累加 最终获得所有的房间数量-->
                <span style="display: none">{{numberhome =0 }}</span>
                <div v-for="item in scope.row.floorsByFid" >
                 <span style="display: none" v-if="item.floorsByFid!=null">
                    {{numberhome = numberhome+item.floorsByFid.length}}
                  </span>
                </div>
                {{numberhome}}
              </template >
            </el-table-column>
            <el-table-column  label="操作">
              <template slot-scope="scope">
                <el-button size="mini" @click="updateNameByFid(scope.row.fid)"><i class="el-icon-edit" style="margin-right: 5px"></i>修改名称</el-button>
                <el-button size="mini" @click="deldetByFid(scope.row.fid)"><i class="el-icon-remove-outline" style="margin-right: 5px"></i>删除</el-button>
              </template >
            </el-table-column>
          </el-table>
        </el-main>
      </el-container>

      <!--新增楼层-->
      <el-dialog
        title="提示"
        :visible.sync="dialogVisiblelc"
        width="50%"
        :before-close="handleClose">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-row>
            <el-col :span="12">
              <el-form-item label="楼层名称" prop="fname">
                <el-input v-model="ruleForm.fname" style="width: 200px;"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="房间数量" prop="fjnumber">
                <el-input v-model="ruleForm.fjnumber" type="number" style="width: 200px;"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="7">
              <el-form-item label="房间名称前缀" prop="mcqz">
                <el-input v-model="ruleForm.mcqz"  style="width: 100px"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="房间名称格式" >
                <el-input  :disabled="true"  style="width: 200px" value="几零几(XYZ)">
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="7">
              <el-form-item label="房间名称后缀" prop="mchz">
                <el-input v-model="ruleForm.mchz"  style="width: 100px"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="床位数量" prop="cwnumber">
                <el-input  style="width: 200px" v-model="ruleForm.cwnumber">
                </el-input>/房间
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="床位类型" prop="cwtype">
                <el-select v-model="ruleForm.cwtype" filterable placeholder="请选择" >
                  <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                    <span style="float: left">{{ item.label }}</span>
                    <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value}}</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item>
            <el-button type="primary" @click="submitFormlc('ruleForm')">立即创建</el-button>
            <el-button @click="resetFormlc('ruleForm')">取消</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
      <el-container  v-show="stepone">
        <el-header style="text-align: left; font-size: 12px">
          <el-button @click="addlouchen"><i class="el-icon-circle-plus-outline" style="margin-right: 5px" ></i>新增楼层</el-button>
        </el-header>
        <el-main>
          <el-table :data="tableData">

            <el-table-column prop="fname" label="楼层名称" >
            </el-table-column>
            <el-table-column prop="floorsByFid.length" label="房间数量">
            </el-table-column>
            <el-table-column  label="操作">
              <template slot-scope="scope">
                <el-button size="mini"  @click="updateNameByFid(scope.row.fid)"><i class="el-icon-edit" style="margin-right: 5px"></i>修改名称</el-button>
                <el-button size="mini" @click="deldetByFid(scope.row.fid)"><i class="el-icon-remove-outline" style="margin-right: 5px"></i>删除</el-button>

              </template >
            </el-table-column>
          </el-table>
        </el-main>
      </el-container>

      <!--新增房间-->
      <el-dialog
        title="提示"
        :visible.sync="dialogVisiblefj"
        width="50%"
        :before-close="handleClose">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-row>
            <el-col :span="12">
              <el-form-item label="房间名称" prop="fname">
                <el-input v-model="ruleForm.fname" style="width: 200px;"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="床位数量" prop="cwnumber">
                <el-input  style="width: 200px" v-model="ruleForm.cwnumber">
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="床位类型" prop="cwtype">
                <el-select v-model="ruleForm.cwtype" filterable placeholder="请选择" >
                  <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                    <span style="float: left">{{ item.label }}</span>
                    <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value}}</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item>
            <el-button type="primary" @click="submitFormfj('ruleForm')">立即创建</el-button>
            <el-button @click="resetFormfj('ruleForm')">取消</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
      <el-container  v-show="steptwo">
        <el-header style="text-align: left; font-size: 12px">
          <el-button @click="addfangjain"><i class="el-icon-circle-plus-outline" style="margin-right: 5px"></i>新增房间</el-button>

        </el-header>
        <el-main>
          <el-table :data="tableData"tooltip-effect="dark"  @selection-change="handleSelectionChange">
            <el-table-column
              type="selection"
              width="55">
            </el-table-column>
            <el-table-column prop="fname" label="房间名称">
            </el-table-column>
            <el-table-column  label="操作">
              <template slot-scope="scope">
                <el-button size="mini"  @click="updateNameByFid(scope.row.fid)"><i class="el-icon-edit" style="margin-right: 5px"></i>修改名称</el-button>
                <el-button size="mini" @click="deldetByFidfj(scope.row.fid)"><i class="el-icon-remove-outline" style="margin-right: 5px"></i>删除</el-button>

              </template >
            </el-table-column>
          </el-table>
        </el-main>
      </el-container>

      <!--新增床位-->
      <el-dialog
        title="提示"
        :visible.sync="dialogVisiblecw"
        width="50%"
        :before-close="handleClose">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-row>
            <el-col :span="12">
              <el-form-item label="床位名称" prop="fname">
                <el-input v-model="ruleForm.fname" style="width: 200px;"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="床位类型" prop="cwtype">
                <el-select v-model="ruleForm.cwtype" filterable placeholder="请选择" >
                  <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                    <span style="float: left">{{ item.label }}</span>
                    <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value}}</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item>
            <el-button type="primary" @click="submitFormcw('ruleForm')">立即创建</el-button>
            <el-button @click="resetFormcw('ruleForm')">取消</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
      <el-container  v-show="steplost">
        <el-header style="text-align: left; font-size: 12px">
          <el-button @click="addchuangwei"><i class="el-icon-circle-plus-outline" style="margin-right: 5px"></i>新增床位</el-button>
        </el-header>
        <el-main>

           <fieldset>
             <legend>{{fname}}</legend>
             <div v-if="bedList!=[]" v-for="i in bedList" >


               <fieldset v-if="i.myoldman==null" style="line-height: 3;width:300px;height: 300px;margin:20px;float: left;border: 1px solid gray">
                 <legend>{{i.bname}}</legend>
                 <span>{{i.bedtypeByByid.byname}}</span>
                 <p></p>
                 床位空闲
                 <p></p>
                 <el-button size="mini" @click="deldetByFidcw(i)"><i class="el-icon-remove-outline" style="margin-right: 5px"></i>删除床位</el-button>
               </fieldset>

                 <fieldset v-if="i.myoldman!=null" style="line-height: 3;width:300px;height: 300px;margin:20px;float: left;border: 1px solid gray">
                   <legend>{{i.bname}}</legend>
                   <p></p>
                   {{i.bedtypeByByid.byname}}
                   <p></p>

                   <span v-if="i.myoldman.tomSex=='男'">
                     <i class="el-icon-user-solid" style="color: #0090ff"></i><span>{{i.myoldman.tomName}}</span>
                   </span>
                   <span v-if="i.myoldman.tomSex=='女'">
                     <i class="el-icon-user-solid" style="color: pink"></i><span>{{i.myoldman.tomName}}</span>
                   </span>
                   <p></p>
                 </fieldset>

             </div>
               <div v-if="bedList.length==0" style="height: 300px;text-align: center;line-height: 30px">
                 该房间暂时还未添加床位
               </div>
           </fieldset>



        </el-main>
      </el-container>


    </el-container>



  </div>
</template>

<script>
  export default {
    name: "Floor",
    data(){
      return{
        floortree:[],
        tableData:[],
        data: [
            {
          label: '一级 1',
          children: [{
            label: '二级 1-1',
            children: [{
              label: '三级 1-1-1'
            }]
          }]
            }, {
          label: '一级 2',
          children: [{
            label: '二级 2-1',
            children: [{
              label: '三级 2-1-1'
            }]
          }, {
            label: '二级 2-2',
            children: [{
              label: '三级 2-2-1'
            }]
          }]
            }, {
          label: '一级 3',
          children: [{
            label: '二级 3-1',
            children: [{
              label: '三级 3-1-1'
            }]
          }, {
            label: '二级 3-2',
            children: [{
              label: '三级 3-2-1'
            }]
          }]
        }
        ],
        defaultProps: {
          children: 'floorsByFid',
          label: 'fname'
        },
        /*初始详细信息*/
        stepthree:true,
        /*初始详细信息*/
        stepone:false,
        /*点击楼层显示详情数据*/
        steptwo:false,
        steplost:false,
        /*被选中的信息*/
        multipleSelection :[],
        /*添加楼栋的from 对话*/
        dialogVisible:false,
        rules: {
          fname: [
            { required: true, message: '请输入楼栋名称', trigger: 'blur' }
          ],
          lcnumber: [
            { required: true, message: '请输楼层数量', trigger: 'blur' }
          ],
          fjnumber: [
            { required: true, message: '请输房间数量', trigger: 'blur' }
          ],
          cwnumber:[
            { required: true, message: '请输床位数量', trigger: 'blur' }
          ],
          cwtype:[
            { required: true, message: '请选择', trigger: 'change' }
          ],
          lcstyle:[
            { required: true, message: '请选择', trigger: 'change' }
          ]
        },
        ruleForm:{
          fname:'',
          lcnumber:'',
          fjnumber:'',
          lcstyle:'',
          mcqz:'',
          mchz:'',
          cwnumber:'',
          cwtype:'',
        },
        /*新增楼层*/
        dialogVisiblelc:false,
        fid:0,
        /*新增房间*/
        dialogVisiblefj:false,
        bedType:[],
        options:[],
        fname :'',
        bedList:[],
        /*新增床位*/
        dialogVisiblecw:false,
      }
    },
    methods:{
      deldetByFidcw(r){
        this.$confirm('此操作将永久删除, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          console.log(r)
          this.$axios.post("http://localhost:8089/floor/deldetByFidcw",this.$qs.stringify({
            bid : r.bid,
          }))
            .then(r=>{
              console.log(r);
                this.$message({
                  type: 'success',
                  message: '删除成功 '
                });
              this.steplost = false
              this.stepthree = true
              this.dialogVisiblecw =false;
              this.tree();
            })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      /*删除房间*/
      deldetByFidfj(r){
        this.$confirm('此操作将永久删除, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          console.log(r)
          this.$axios.post("http://localhost:8089/floor/deldetByFidfj",this.$qs.stringify({
            fid : r,
          }))
            .then(r=>{
              console.log(r);
              if(r==2){
                this.$message({
                  type: 'success',
                  message: '删除成功 '
                });
              }
              if(r==1){
                this.$message({
                  type: 'warning',
                  message: '删除失败:有床位有人老人住 '
                });
              }
              this.tree();
            })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      /*删除楼层*/
      deldetByFidlc(r){
        this.$confirm('此操作将永久删除, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.post("http://localhost:8089/floor/deldetByFidlc",this.$qs.stringify({
            fid : r,
          }))
            .then(r=>{
              console.log(r);
              if(r==2){
                this.$message({
                  type: 'success',
                  message: '删除成功 '
                });
              }
              if(r==1){
                this.$message({
                  type: 'warning',
                  message: '删除失败:有床位有人老人住 '
                });
              }
              this.tree();
            })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      /*删除*/
      deldetByFid(r){
        this.$confirm('此操作将永久删除, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.post("http://localhost:8089/floor/deldetByFid",this.$qs.stringify({
            fid : r,
          }))
            .then(r=>{
              console.log(r);
              if(r==2){
                this.$message({
                  type: 'success',
                  message: '删除成功 '
                });
              }
              if(r==1){
                this.$message({
                  type: 'warning',
                  message: '删除失败:有床位有人老人住 '
                });
              }
              this.tree();
            })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      /*修改名称*/
      updateNameByFid(r){
        this.$prompt('请输入名字', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          inputPattern: /\S/,
          inputErrorMessage: '请输入名称'
        }).then(({ value }) => {
          this.$axios.post("http://localhost:8089/floor/updateNameByFid",this.$qs.stringify({
            fname : value,
            fid : r,
          }))
            .then(r=>{
              this.tree();
              this.$message({
                type: 'success',
                message: '修改成功: '
              });
            })

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消输入'
          });
        });
        console.log(r)
      },
      addchuangwei(){
        this.ruleForm={
          fname : '',
          lcnumber:'',
          fjnumber:'',
          lcstyle:'',
          mcqz:'',
          mchz:'',
          cwnumber:'',
          cwtype:''
        }
        this.dialogVisiblecw = true
      },
      /* 添加床位的from验证 新增*/
      submitFormcw(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            console.log(this.ruleForm)
            console.log(this.fid);
            this.$axios.post("http://localhost:8089/floor/insertChuangwei",this.$qs.stringify({
              fname : this.ruleForm.fname,
              fjnumber : this.ruleForm.fjnumber,
              lcnumber : this.ruleForm.lcnumber,
              lcstyle : this.ruleForm.lcstyle,
              mchz : this.ruleForm.mchz,
              mcqz : this.ruleForm.mcqz,
              fid : this.fid,
              cwnumber:this.ruleForm.cwnumber,
              cwtype:this.ruleForm.cwtype,
            }))
              .then(r=>{
                this.tree();
                this.steplost = false
                this.stepthree = true
                this.dialogVisiblecw =false;
              })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      /*添加床位的from 关闭对话*/
      resetFormcw(formName) {
        this.$refs[formName].resetFields();
        this.dialogVisiblecw =false;
      },
      /*根据fid房间id 查找床位*/
      selectByFid(){
        console.log(this.fid);
        console.log(this.tableData);
        this.$http.post("http://localhost:8089/floor/selectByFid",this.$qs.stringify({fid:this.fid}))
          .then((res) => {
            this.bedList = res;
            console.log(res)
          })
      },
      /*获取床位类型*/
      selectBedType(){
        this.$axios.post("http://localhost:8089/bedType/selectAll").then(r=>{
          this.bedType = r;
          this.bedType.forEach(v => {
            var json = {
              value: v.byid,
              label: v.byname
            };
            this.options.push(json);
          });
          console.log(this.options);
        })
      },
      /*打开 添加房间的from 对话*/
      addfangjain(){
        this.ruleForm={
          fname : '',
          lcnumber:'',
          fjnumber:'',
          lcstyle:'',
          mcqz:'',
          mchz:'',
          cwnumber:'',
          cwtype:''
        }
        this.dialogVisiblefj = true
      },
      /* 添加房间的from验证 新增*/
      submitFormfj(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            console.log(this.ruleForm)
            console.log(this.fid);
            this.$axios.post("http://localhost:8089/floor/insertFangjian",this.$qs.stringify({
              fname : this.ruleForm.fname,
              fjnumber : this.ruleForm.fjnumber,
              lcnumber : this.ruleForm.lcnumber,
              lcstyle : this.ruleForm.lcstyle,
              mchz : this.ruleForm.mchz,
              mcqz : this.ruleForm.mcqz,
              fid : this.fid,
              cwnumber:this.ruleForm.cwnumber,
              cwtype:this.ruleForm.cwtype,
            }))
              .then(r=>{
                this.tree();
                this.dialogVisiblefj =false;
              })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      /*添加房间的from 关闭对话*/
      resetFormfj(formName) {
        this.$refs[formName].resetFields();
        this.dialogVisiblefj =false;
      },

      /*打开 添加楼层的from 对话*/
      addlouchen(){
        this.ruleForm={
          fname : '',
          lcnumber:'',
          fjnumber:'',
          lcstyle:'',
          mcqz:'',
          mchz:'',
          cwnumber:'',
          cwtype:'',
        }
        this.dialogVisiblelc = true
      },
      /* 添加楼层的from验证 新增*/
      submitFormlc(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            console.log(this.ruleForm)
            console.log(this.fid);
            this.$axios.post("http://localhost:8089/floor/insertLouchen",this.$qs.stringify({
              fname : this.ruleForm.fname,
              fjnumber : this.ruleForm.fjnumber,
              lcnumber : this.ruleForm.lcnumber,
              lcstyle : this.ruleForm.lcstyle,
              mchz : this.ruleForm.mchz,
              mcqz : this.ruleForm.mcqz,
              fid : this.fid,
              cwnumber:this.ruleForm.cwnumber,
              cwtype:this.ruleForm.cwtype
            }))
              .then(r=>{
                this.tree();
                this.dialogVisiblelc =false;
              })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      /*添加楼栋的from 关闭对话*/
      resetFormlc(formName) {
        this.$refs[formName].resetFields();
        this.dialogVisiblelc =false;
      },

      /* 添加楼栋的from验证 新增*/
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            console.log(this.ruleForm)
            this.$axios.post("http://localhost:8089/floor/insertLoudong",this.$qs.stringify({
              fname : this.ruleForm.fname,
              fjnumber : this.ruleForm.fjnumber,
              lcnumber : this.ruleForm.lcnumber,
              lcstyle : this.ruleForm.lcstyle,
              mchz : this.ruleForm.mchz,
              mcqz : this.ruleForm.mcqz,
              cwnumber:this.ruleForm.cwnumber,
              cwtype:this.ruleForm.cwtype,
            }))
              .then(r=>{
                this.tree();
                    this.dialogVisible =false;

              })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      /*添加楼栋的from 关闭对话*/
      resetForm(formName) {
        this.$refs[formName].resetFields();
        this.dialogVisible =false;
      },
      /*打开 添加楼栋的from 对话*/
      addloudong(){
        this.ruleForm={
          fname : '',
          lcnumber:'',
          fjnumber:'',
          lcstyle:'',
          mcqz:'',
          mchz:'',
          cwnumber:'',
          cwtype:'',
        }
        this.dialogVisible = true
      },
      /*询问 添加楼栋的from 对话 是否关闭*/
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
      /*树形控件的点击事件*/
      handleNodeClick(data) {

        if(data.ftype == 0){
          this.stepone = false;
          this.steptwo = false;
          this.stepthree = true;
          this.steplost = false
          this.tableData=data.floorsByFid;

        }else if (data.ftype == 1){
          this.stepone = true;
          this.steptwo = false;
          this.stepthree = false;
          this.steplost = false;
          this.tableData=data.floorsByFid;
          this.fid = data.fid;
        }else if(data.ftype == 2){
          this.stepone = false;
          this.steptwo = true;
          this.stepthree = false;
          this.steplost = false;
          this.tableData=data.floorsByFid;
          this.fid = data.fid;

        }else if (data.ftype == 3){
          this.stepone = false;
          this.steptwo = false;
          this.stepthree = false;
          this.steplost = true;
          this.fid = data.fid;
          this.fname = data.fname;
          this.selectByFid();
        }
      },

      /*查询floor的树形数据*/
      tree(){
        this.$http.post("http://localhost:8089/floor/tree" )
          .then((res) => {
            this.floortree = res.floors;
            this.tableData = res.floors[0].floorsByFid;
          })
      },

    },
    created() {
      this.numberhome=0;
      this.tree();
      this.selectBedType();
    }
  }
</script>

<style scoped>
  .el-aside {
    border: 1px solid #B3C0D1;
    background-color: white;
    color: #333;
    text-align: center;
    height: 680px;
  }

  .el-main {
    background-color: white;
    color: #333;
    text-align: center;
    height: 680px;
  }

  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

</style>
