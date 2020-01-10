<template>
	<section>
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters">
				<el-form-item>
					<el-input v-model="filters.name" size="small" clearable='true' placeholder="任务名称"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" size="small" v-on:click="getUsers">查询</el-button>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" size="small" @click="handleAdd">新增</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!--列表-->
		<el-table :data="users" highlight-current-row v-loading="listLoading" @selection-change="selsChange" max-height="500" style="width: 100%;">
			<el-table-column type="selection" width="55">
			</el-table-column>
			<el-table-column type="index" width="60">
			</el-table-column>
			<el-table-column prop="jsUserName" label="接收人" width="120" sortable>
			</el-table-column>
			<el-table-column prop="fbUserName" label="发布人" width="120" sortable>
			</el-table-column>
			<el-table-column prop="rwmc" label="任务名" width="180">
			</el-table-column>
			<el-table-column prop="jjcd" label="任务紧急程度" width="200" :formatter="formatUrgent" sortable>
			</el-table-column>
			<el-table-column prop="rwTime" label="截止时间" sortable>
			</el-table-column>
			<!-- <el-table-column prop="rwms" label="任务描述">
			</el-table-column> -->
			<el-table-column label="操作" width="150">
				<template scope="scope">
					<el-button size="small" @click="handleEdit(scope.$index, scope.row)">详情</el-button>
					<el-button type="danger" size="small" v-if="ifAdmin" @click="handleDel(scope.$index, scope.row)">删除</el-button>
				</template>
			</el-table-column>
		</el-table>

		<!--工具条-->
		<el-col :span="24" class="toolbar">
			<el-button type="danger" size="small" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>
			<!-- <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
			</el-pagination> -->
		</el-col>

		<!--编辑界面-->
		<el-dialog title="编辑" v-model="editFormVisible" :close-on-click-modal="false">
			<el-form :model="editForm" label-width="120px" :rules="editFormRules" ref="editForm">
				<el-form-item label="接收人" prop="jsUserId">
					<el-select v-model="editForm.jsUserId">
						<el-option
							v-for="item in userData"
							:key="item.userId"
							:label="item.userName"
							:value="item.userId">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="任务名">
					<el-input v-model="editForm.rwmc"></el-input>
				</el-form-item>
				<el-form-item label="任务紧急程度">
					<el-select v-model="editForm.jjcd">
						<el-option label="紧急" value="1"></el-option>
						<el-option label="适中" value="2"></el-option>
						<el-option label="不紧急" value="3"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="截止时间">
					<el-date-picker type="date" placeholder="选择日期" v-model="editForm.rwTime"></el-date-picker>
				</el-form-item>
				<el-form-item label="备注">
					<el-input type="textarea" v-model="editForm.rwms"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="editFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
			</div>
		</el-dialog>

		<!--新增界面-->
		<el-dialog title="新增" v-model="addFormVisible" :close-on-click-modal="false">
			<el-form :model="addForm" label-width="100px" :rules="addFormRules" ref="addForm">
				<el-form-item label="姓名" prop="jsUserId">
					<el-select v-model="addForm.jsUserId">
						<el-option
							v-for="item in userData"
							:key="item.userId"
							:label="item.userName"
							:value="item.userId">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="任务名">
					<el-input v-model="addForm.rwmc"></el-input>
				</el-form-item>
				<el-form-item label="任务紧急程度">
					<el-select v-model="addForm.jjcd">
						<el-option label="紧急" value="1"></el-option>
						<el-option label="适中" value="2"></el-option>
						<el-option label="不紧急" value="3"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="截止时间">
					<el-date-picker type="date" placeholder="选择日期" v-model="addForm.rwTime"></el-date-picker>
				</el-form-item>
				<el-form-item label="任务描述">
					<el-input type="textarea" v-model="addForm.rwms"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="addFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
			</div>
		</el-dialog>
	</section>
</template>

<script>
	import util from '../../common/js/util'
	//import NProgress from 'nprogress'
	import { getUserList , getUserListPage, removeUser, batchRemoveUser, editUser, addUser , getRw } from '../../api/api';

	export default {
		data() {
			return {
				filters: {
					name: ''
				},
				users: [],
				userData: [],
				total: 0,
				page: 1,
				listLoading: false,
				sels: [],//列表选中列
				ifAdmin: true,
				editFormVisible: false,//编辑界面是否显示
				editLoading: false,
				editFormRules: {
					name: [
						{ required: true, message: '请输入姓名', trigger: 'blur' }
					]
				},
				//编辑界面数据
				editForm: {
					id: 0,
					jsUserId: '',
					name: '',
					rwmc: '',
					jjcd: '',
					rwTime: '',
					rwms: ''
				},

				addFormVisible: false,//新增界面是否显示
				addLoading: false,
				addFormRules: {
					name: [
						{ required: true, message: '请输入姓名', trigger: 'blur' }
					]
				},
				//新增界面数据
				addForm: {
					jsUserId: '',
					name: '',
					rwmc: '',
					jjcd: 1,
					rwTime: '',
					rwms: ''
				}

			}
		},
		methods: {
			//性别显示转换
			formatSex: function (row, column) {
				return row.sex == 1 ? '男' : row.sex == 0 ? '女' : '未知';
			},
			formatUrgent(row) {
				if(row.jjcd === 1) {
					return '紧急'
				}else if(row.jjcd === 2) {
					return '适中'
				}else if(row.jjcd === 3) {
					return '不紧急'
				}
			},
			handleCurrentChange(val) {
				this.page = val;
				this.getUsers();
			},
			//获取用户列表
			getUsers() {
				var user = sessionStorage.getItem('user');
				user = JSON.parse(user)
				let para = {
					jsUserId: user.userId,
					rwmc: this.filters.name
				};
				this.listLoading = true;
				//NProgress.start();
				getRw(para).then((res) => {
					// this.total = res.data.total;
					this.users = res;
					this.listLoading = false;
					//NProgress.done();
				});
			},
			//删除
			handleDel: function (index, row) {
				this.$confirm('确认删除该记录吗?', '提示', {
					type: 'warning'
				}).then(() => {
					this.listLoading = true;
					//NProgress.start();
					let para = { id: row.id };
					removeUser(para).then((res) => {
						this.listLoading = false;
						//NProgress.done();
						this.$message({
							message: '删除成功',
							type: 'success'
						});
						this.getUsers();
					});
				}).catch(() => {

				});
			},
			//显示编辑界面
			handleEdit: function (index, row) {
				console.log(row);
				this.editFormVisible = true;
				this.editForm = Object.assign({}, row);
				this.editForm.jjcd = String(this.editForm.jjcd)
			},
			//显示新增界面
			handleAdd: function () {
				this.addFormVisible = true;
				this.addForm = {
					jsUserId: '',
					name: '',
					rwmc: '',
					jjcd: '',
					rwTime: '',
					rwms: ''
				};
			},
			//编辑
			editSubmit: function () {
				this.$refs.editForm.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.editLoading = true;
							//NProgress.start();
							let para = Object.assign({}, this.editForm);
							para.rwTime = (!para.rwTime || para.rwTime == '') ? '' : util.formatDate.format(new Date(para.rwTime), 'yyyy-MM-dd');
							editUser(para).then((res) => {
								this.editLoading = false;
								//NProgress.done();
								this.$message({
									message: '提交成功',
									type: 'success'
								});
								this.$refs['editForm'].resetFields();
								this.editFormVisible = false;
								this.getUsers();
							});
						});
					}
				});
			},
			//新增
			addSubmit: function () {
				this.$refs.addForm.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.addLoading = true;
							//NProgress.start();
							let para = Object.assign({}, this.addForm);
							para.rwTime = (!para.rwTime || para.rwTime == '') ? '' : util.formatDate.format(new Date(para.rwTime), 'yyyy-MM-dd');
							addUser(para).then((res) => {
								this.addLoading = false;
								//NProgress.done();
								this.$message({
									message: '提交成功',
									type: 'success'
								});
								this.$refs['addForm'].resetFields();
								this.addFormVisible = false;
								this.getUsers();
							});
						});
					}
				});
			},
			selsChange: function (sels) {
				this.sels = sels;
			},
			//批量删除
			batchRemove: function () {
				var ids = this.sels.map(item => item.id).toString();
				this.$confirm('确认删除选中记录吗？', '提示', {
					type: 'warning'
				}).then(() => {
					this.listLoading = true;
					//NProgress.start();
					let para = { ids: ids };
					batchRemoveUser(para).then((res) => {
						this.listLoading = false;
						//NProgress.done();
						this.$message({
							message: '删除成功',
							type: 'success'
						});
						this.getUsers();
					});
				}).catch(() => {

				});
			},
			//获取接收人list
			getUserData() {
				var obj = {
					userId: 123
				}
				getUserList(obj).then((res) => {
					console.log(res,'res')
					this.userData = res.data
				})
			},
		},
		mounted() {
			var user = sessionStorage.getItem('user');
			user = JSON.parse(user)
			user.permission=='1'?this.ifAdmin = true:this.ifAdmin = false
			this.getUsers();
			this.getUserData()
		}
	}

</script>

<style scoped>

</style>