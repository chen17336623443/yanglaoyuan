<template>
	<div class="div">

		<el-row :gutter="20" class="goodsindex-queryInfo" v-show="show">


			<el-col :xs="8" :sm="6" :md="6" :lg="4" :xl="4">
				<el-input class="goodsindex-queryInfo-li" v-model="juese.name" @keyup.enter.native="pageNum=0; shuju()" clearable size="mini" placeholder="输入名称"></el-input>
			</el-col>

			<el-col :span="9.5">

				<div class="block" style="font-size: 12px;">

					创建日期：<el-date-picker @change="pageNum=1;loadData()" size="mini" value-format="yyyy-MM-dd" v-model="juese.hiredate"
					 type="datetimerange" :picker-options="pickerOptions" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期"
					 align="right">
					</el-date-picker>
				</div>

			</el-col>




			<el-col :xs="6" :sm="4" :md="3" :lg="2" :xl="2">

				<el-button type="primary" icon="el-icon-search" size="mini" @click="pageNum=0; shuju()">搜索</el-button>
			</el-col>
			<el-col :xs="6" :sm="4" :md="3" :lg="2" :xl="2">
				<el-button type="warning" class="goodsindex-queryInfo-li" size="mini" @click="juese.name=''">重置</el-button>
			</el-col>


		</el-row>


		<el-row style="margin-top: 10px;">

			<el-col :span="24">
				<el-row>
					<el-col :span="2">

						<el-button type="primary" icon="el-icon-plus" size="mini" @click="outerVisible = true;update=false;shuaxin();">新增</el-button>
					</el-col>
					<el-col :span="3">
						<el-button type="warning" icon="el-icon-download" class="goodsindex-queryInfo-li" size="mini" @click="">导出</el-button>
					</el-col>

					<el-col :span="18">

						<el-button-group style="float: right;">
							<el-button type="primary" icon="el-icon-search" @click="show=!show"></el-button>
							<el-button type="primary" icon="el-icon-refresh"></el-button>
							<el-button type="primary" icon="el-icon-s-operation"></el-button>
						</el-button-group>
					</el-col>

				</el-row>




			</el-col>

		</el-row>

		<!--  左侧内容-->
		<el-row :gutter="8">
			<el-col :span="24">
				<el-card class="box-card">
					<div slot="header" class="clearfix">
						<span>咨询列表</span>
						<el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
					</div>
					<el-row>

						<el-col :span="24">
							<el-table :data="tableData1.list" style="width: 100%" row-key="id" border lazy :load="load" :tree-props="{menus: 'menus', hasChildren: 'hasChildren'}">
								<el-table-column prop="cId" label="编号" width="60">
								</el-table-column>
								<el-table-column prop="title" label="名称">
								</el-table-column>
								<el-table-column prop="component" label="路由地址">
								</el-table-column>
								<el-table-column prop="icon" width="60" label="路由图标">
									<template slot-scope="scope">
										<i :class="scope.row.icon" style="font-size: 24px;"></i>
									</template>
								</el-table-column>
								<el-table-column prop="dengji" label="路由级别">
								</el-table-column>

								<el-table-column prop="mymenu.title" label="路由父级">
								</el-table-column>

								<el-table-column prop="" label="操作">
									<template slot-scope="scope">
										<el-button type="primary" icon="el-icon-edit" circle @click="mymenu=(scope.row.mymenu!=null?scope.row:jiazhi(scope.row));outerVisible=true;update=true;"></el-button>
										<el-button type="danger" icon="el-icon-delete" circle @click="deletebymid(scope.row.mid)"></el-button>
									</template>
								</el-table-column>
							</el-table>


							<!-- <div style="margin-top: 20px">
								<el-button @click="toggleSelection([tableData[1], tableData[2]])">切换第二、第三行的选中状态</el-button>
								<el-button @click="toggleSelection()">取消选择</el-button>
							</div> -->
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<div class="block">

								<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page=tableData1.pageNum
								 :page-sizes="[1, 5, 10, 50]" :page-size=tableData1.pageSize layout="total, sizes, prev, pager, next, jumper"
								 :total=tableData1.total>
								</el-pagination>
							</div>


						</el-col>
					</el-row>
				</el-card>
			</el-col>

			
		</el-row>



		<!-- 新增菜单 -->
		<template>


			<el-dialog :title="update==true?'修改菜单':'新增菜单'" :visible.sync="outerVisible">


				<el-dialog width="30%" title="咨询列表" :visible.sync="innerVisible" append-to-body>
					<el-row>
						<el-col :span="24">
							<el-input placeholder="输入关键字进行过滤" v-model="filterText">
							</el-input>

						</el-col>
						<el-col :span="24">
							<el-tree :data="menus" show-checkbox :filter-node-method="filterNode" :default-expand-all="false" node-key="mid" ref="tree"
							 highlight-current :props="defaultProps" :check-strictly="true">
							</el-tree>


						</el-col>
					</el-row>
					</el-card>

					</el-col>

					<div class="buttons" style="margin-top: 20px;">

						<el-button type="primary" @click="getCheckedNodes()" size="mini" class="goodsindex-queryInfo-li">确定</el-button>

						<el-button type="warning" @click="" size="mini" style="margin-left: 20px;">取消</el-button>
					</div>
					</el-row>
				</el-dialog>
				<div slot="footer" class="dialog-footer">
				


					<el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
						<el-form-item label="菜单名称">
							<el-input v-model="mymenu.title"></el-input>
						</el-form-item>
						<el-form-item label="菜单图标">
							<el-input v-model="mymenu.icon"></el-input>
						</el-form-item>
						<el-form-item label="路由地址">
							<el-input v-model="mymenu.component"></el-input>
						</el-form-item>
						<el-form-item label="上级菜单">
							<el-input style="width: 60%; float: left;" placeholder="请点击按钮进行选择" v-model="mymenu.mymenu.title" :disabled="true">
							</el-input>
							<el-button type="primary" @click="innerVisible = true" style="float: left;">选择</el-button>

						</el-form-item>
						<el-form-item label="级别">

							<el-select v-model="mymenu.dengji" placeholder="菜单级别" style="float: left;">
								<el-option label="2" value="2"></el-option>
								<el-option label="3" value="3"></el-option>
								<el-option label="4" value="4"></el-option>

							</el-select>
						</el-form-item>

					</el-form>




					<el-button @click="outerVisible = false">取 消</el-button>
					<el-button type="warning" @click="insertmenu()">{{update==true?'修改':'新增'}}</el-button>
				</div>
			</el-dialog>
		</template>



	</div>
</template>

<script>
	export default {
		data() {
			return {
				update: false,

				dangemenu: {},
				labelPosition: 'left',

				mymenu: {
					title: '',
					component: '',
					dengji: 0,
					mymenu: {
						title: '',
						mid: ''
					},
					icon: '',
				},



				formLabelAlign: {
					input: {
						title: ''
					},
					tubiao: '',
					name: '',
					region: '',
					type: ''
				},

				outerVisible: false,
				innerVisible: false,

				menus: [],


				tableData: [{
					id: 1,
					date: '2016-05-02',
					name: '王小虎',
					address: '上海市普陀区金沙江路 1518 弄'
				}, {
					id: 2,
					date: '2016-05-04',
					name: '王小虎',
					address: '上海市普陀区金沙江路 1517 弄'
				}, {
					id: 3,
					date: '2016-05-01',
					name: '王小虎',
					address: '上海市普陀区金沙江路 1519 弄',
					children: [{
						id: 31,
						date: '2016-05-01',
						name: '王小虎',
						address: '上海市普陀区金沙江路 1519 弄'
					}, {
						id: 32,
						date: '2016-05-01',
						name: '王小虎',
						address: '上海市普陀区金沙江路 1519 弄'
					}]
				}, {
					id: 4,
					date: '2016-05-03',
					name: '王小虎',
					address: '上海市普陀区金沙江路 1516 弄'
				}],
				tableData1: [{
					id: 1,
					date: '2016-05-02',
					name: '王小虎',
					address: '上海市普陀区金沙江路 1518 弄'
				}, {
					id: 2,
					date: '2016-05-04',
					name: '王小虎',
					address: '上海市普陀区金沙江路 1517 弄'
				}, {
					id: 3,
					date: '2016-05-01',
					name: '王小虎',
					address: '上海市普陀区金沙江路 1519 弄',
					hasChildren: true
				}, {
					id: 4,
					date: '2016-05-03',
					name: '王小虎',
					address: '上海市普陀区金沙江路 1516 弄'
				}],
				filterText: '',


				defaultProps: {
					children: 'menus',
					label: 'title'
				},
				zhiweis: [],



				multipleSelection: [],
				show: true,
				juese: {
					name: '',
					hiredate: '',
				},
				pageNum: 1,
				pageSize: 5,

				pickerOptions: {
					shortcuts: [{
						text: '最近一周',
						onClick(picker) {
							const end = new Date();
							const start = new Date();
							start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
							picker.$emit('pick', [start, end]);
						}
					}, {
						text: '最近一个月',
						onClick(picker) {
							const end = new Date();
							const start = new Date();
							start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
							picker.$emit('pick', [start, end]);
						}
					}, {
						text: '最近三个月',
						onClick(picker) {
							const end = new Date();
							const start = new Date();
							start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
							picker.$emit('pick', [start, end]);
						}
					}]
				}
			}

		},
		created() {
			// this.loadData();
			this.shuju();
			// this.menu();
		},
		watch: {
			filterText(val) {
				this.$refs.tree.filter(val);
			}
		},
		methods: {
			
			
		/*	<!-- 当某个菜单没有上级是要给设为'' */
			jiazhi(a){
				a.mymenu={title:''};
				return a;
				
				
				
			},

			/* 新增刷新 */
			shuaxin() {
				this.mymenu = {
					title: '',
					component: '',
					dengji: '',
					mymenu: {
						title: '',
						mid: ''
					},
					icon: '',
				}
			},

			/* 修改菜单 */


			/* 点击修改按钮传值 */



			/* 删除某个菜单 */
			deletebymid(mid) {
				this.$axios.get("http://localhost:8085/menu/deletemenu?mid=" + mid)
					.then(res => {
						if (res != 0) {
							this.$message.success("删除成功")
							this.loadData();
							this.shuju();
							this.menu();
						} else {
							this.$message.error("删除失败")
						}
					})
			},

			/* 新增菜单menu */
			insertmenu() {

				if (this.update) {

					this.$axios.post("http://localhost:8085/menu/updatemid", this.mymenu)
						.then(res => {
							if (res != 0) {
								this.$message.success("修改成功")
							} else {
								this.$message.error("修改失败")
							}
						})
						.catch(e => {
							console.log("在修改菜单时候报错了", e)
						})


				} else {

					this.$axios.post("http://localhost:8085/menu/insertmenu", this.mymenu)
						.then(res => {
							if (res != 0) {
								this.$message.success("新增成功")
								this.loadData();

								this.shuju();

								this.menu();
							} else {
								this.$message.error("新增失败")
							}
						})
						.catch(e => {
							console.log("在新增菜单时候报错了", e)
						})
				}
				this.outerVisible = false;



			},

			shuju() {
				this.$axios.get("http://localhost:8089/consult/all?no=" + this.pageNum + "&size=" + this.pageSize +
						"&name=" + this.juese.name)
					.then(res => {
						this.tableData1 = res;
						console.log("菜单数据", res)
					})
					.catch(e => {
						console.log("获取数据菜单时候报错", e)
					})
			},



			load(tree, treeNode, resolve) {
				console.log("tree", tree, "resolve", resolve, "treeNode", treeNode)
				setTimeout(() => {
					resolve([
						/*  {
						    id: 31,
						    date: '2016-05-01',
						    name: '王小虎',
						    address: '上海市普陀区金沙江路 1519 弄'
						  }, {
						    id: 32,
						    date: '2016-05-01',
						    name: '王小虎',
						    address: '上海市普陀区金沙江路 1519 弄'
						  } */
					])
				}, 1000)
			},
			filterNode(value, data) {
				console.log("value", value);
				console.log("data", data);
				if (!value) return true;
				return data.title.indexOf(value) !== -1;
			},

			handleNodeClick(data) {




			},


			/* 树形控件的方法 */
			getCheckedNodes() {
				console.log(this.$refs.tree.getCheckedNodes());

				if ((this.$refs.tree.getCheckedNodes()).length > 1 ) {
					this.$message.warning("只允许一个上级菜单！")
					
				} else if( (this.$refs.tree.getCheckedNodes()).length == 1){
					this.mymenu.mymenu = this.$refs.tree.getCheckedNodes()[0];
					this.innerVisible = false;
				}
				
				else {
						this.mymenu.mymenu ={title:''}
					this.innerVisible = false
				}
			},
			getCheckedKeys() {
				console.log(this.$refs.tree.getCheckedKeys());
				let caidan = this.$refs.tree.getCheckedKeys();

				this.multipleSelection.forEach((zhiwie) => {

					this.$axios.get("http://localhost:8085/zhiweihecaidanbiao/bydeleteall?deptno=" + this.multipleSelection[0].mydept
							.deptno +
							"&positionID=" + zhiwie.positionId)
						.then(res => {

							caidan.forEach((item, index) => {
								let zhiweihecaidanbiao = {
									mymenu: {
										mid: item
									},
									myempzhiwei: {
										positionId: zhiwie.positionId
									},
									mydept: {
										deptno: zhiwie.mydept.deptno
									},


								}


								setTimeout(() => {
									this.$axios.post("http://localhost:8085/zhiweihecaidanbiao/add", zhiweihecaidanbiao)

										.then(res => {
											if (caidan.length - 1 == index) {
												this.$message.success("分配成功")
												this.loadData();
											}

										})

										.catch(e => {
											console.log("发生了错误信息", e)
										})
								}, 200);



							})



						})

						.catch(e => {
							console.log("删除数据时菜单报错", e)
						})

				})

				/* 结束 */
			},
			setCheckedNodes() {
				this.$refs.tree.setCheckedNodes([{
					id: 5,
					label: '二级 2-1'
				}, {
					id: 9,
					label: '三级 1-1-1'
				}]);
			},
			/* setCheckedKeys() {
			   this.$refs.tree.setCheckedKeys([3]);
			 }, */

			setCheckedKeys(shuzu) {


				if (shuzu.length == 0 || shuzu.leng > 1) {
					this.$refs.tree.setCheckedKeys([]);
				} else {
					this.$refs.tree.setCheckedKeys(shuzu);
				}

			},
			resetChecked() {
				this.$refs.tree.setCheckedKeys([]);
			},


			/* 查询出所有的菜单 */

			menu() {
				this.$axios.get("http://localhost:8085/menu/all")
					.then(res => {






						this.menus = res;
						console.log("所有的菜单信息", res)

					})

					.catch(e => {
						console.log(e => {
							console.log("在 获取菜单信息是报错", e)
						})
					})
			},



			handleSizeChange(val) {
				console.log(`每页 ${val} 条`);
				this.pageSize = val;
				this.shuju();
			},
			handleCurrentChange(val) {
				this.pageNum = val;
				console.log(`当前页: ${val}`);
				this.shuju();
			},

			loadData() {


				this.$axios.get("http://localhost:8085/zhiwei/zwall?pageNum=" + this.pageNum + "&pageSize=" + this.pageSize)
					.then(res => {
						this.zhiweis = res
					})
					.catch(e => {
						console.log("在获取职位信息时发生了错误:", e)
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
				console.log("我要的数据", val)

				if (val.length < 2) {
					let shuzu = [];
					if (val.length == 1) {
						if (val[0].zhiweicaidanbiaos.length != 0) {
							val[0].zhiweicaidanbiaos.forEach(item => {
								shuzu.push(item.mymenu.mid)

							})
						}
					}
					this.setCheckedKeys(shuzu);



				} else {
					this.setCheckedKeys([]);
				}
				this.multipleSelection = val;
			}
		}


	}
</script>

<style>
	.text {
		font-size: 14px;
	}

	.item {
		padding: 18px 0;
	}

	.box-card {
		width: 100%;
	}

	.box-card1 {
		width: 100%;
	}

	.clearfix:before,
	.clearfix:after {
		display: table;
		content: "";
	}

	.clearfix:after {
		clear: both
	}
	
	.el-dialog__body{
		padding: 5px 20px;
	}
	.div{
    margin: 0;
    padding: 20px;
}
</style>
