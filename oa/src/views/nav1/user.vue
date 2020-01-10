<template>
	<section>
		<!--工具条-->
		<!-- <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters">
				<el-form-item>
					<el-input v-model="filters.name" size="small" placeholder="会议室编号"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" size="small" v-on:click="getTableData">查询</el-button>
				</el-form-item>
			</el-form>
		</el-col> -->

		<!--列表-->
		<template>
			<el-table :data="users" highlight-current-row v-loading="loading" style="width: 100%;" max-height="550">
				<el-table-column type="index" width="60">
				</el-table-column>
				<el-table-column prop="hysbh" label="会议室编号" width="150" sortable>
				</el-table-column>
				<el-table-column prop="hyszt" label="会议室状态" width="150" sortable>
				</el-table-column>
				<el-table-column prop="bz" label="备注" min-width="180">
				</el-table-column>
				<el-table-column label="操作" width="150" v-if="ifAdmin">
					<template scope="scope">
						<el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>
						<el-button size="small" :disabled="buttonDis(scope.row)" @click="handleNotice(scope.row)">通知</el-button>
					</template>
			</el-table-column>
			</el-table>
		</template>

		<!--编辑界面-->
		<el-dialog title="编辑会议室状态" :visible.sync="editFormVisible" :close-on-click-modal="false">
			<el-form :model="editForm" label-width="100px" ref="editForm">
				<el-form-item label="会议室编号" prop="hysbh">
					<el-input v-model="editForm.hysbh" :disabled='read'></el-input>
				</el-form-item>
				<el-form-item label="会议室状态" prop="hyszt">
					<el-select v-model="editForm.hyszt" @change="changee">
						<el-option label="使用中" value="使用中"></el-option>
						<el-option label="空闲" value="空闲"></el-option>
					</el-select>
				</el-form-item>
				<!-- <el-form-item label="使用时间">
					<el-date-picker type="date" placeholder="选择日期" v-model="editForm.birth"></el-date-picker>
				</el-form-item> -->
				<el-form-item label="备注" prop="bz">
					<el-input type="textarea" v-model="editForm.bz"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="cancel" size="small">取消</el-button>
				<el-button type="primary" size="small" @click.native="editSubmit" :loading="editLoading">提交</el-button>
			</div>
		</el-dialog>

		<!-- 短信通知 -->
		<el-dialog title="短信通知参会人员" :visible.sync="noticeFormVisible" :close-on-click-modal="false">
			<el-form :model="noticeForm" label-width="100px" ref="noticeForm">
				<el-form-item label="会议室编号" prop="hysbh">
					<el-input v-model="noticeForm.hysbh" :disabled='read'></el-input>
				</el-form-item>
				<el-form-item label="参会人员" prop="userId">
					<el-select v-model="noticeForm.userId" multiple>
						<el-option 
							v-for="item in userData"
							:key="item.userId"
							:label="item.userName"
							:value="item.userId">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="会议时间" prop="time">
					<el-time-picker
						arrow-control
						v-model="noticeForm.time"
						value-format='HH-mm-ss'
						placeholder="请选择时间">
					</el-time-picker>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="cancelNotice" size="small">取消</el-button>
				<el-button type="primary" size="small" @click.native="noticeSubmit" :loading="editLoading">提交</el-button>
			</div>
		</el-dialog>
	</section>
</template>
<script>
	import util from '../../common/js/util'
	import { getUserList,getHys,editHys,sendMessage } from '../../api/api';
	export default {
		data() {
			return {
				filters: {
					name: ''
				},
				read: true,
				ifAdmin: true,
				loading: false,
				editLoading: false,
				editFormVisible: false,
				noticeFormVisible: false,
				users: [],
				userData: [],
				editForm: {
					hysbh: '',
					hyszt: '',
					// birth: '',
					bz: ''
				},
				noticeForm: {
					hysbh: '',
					time: '',
					userId: [],
				}
			}
		},
		methods: {
			changee() {
				console.log(this.editForm.hyszt)
			},
			//获取用户列表
			getUserData() {
				var obj = {
					userId: 123
				}
				getUserList(obj).then((res) => {
					console.log(res,'res')
					this.userData = res.data
				})
			},
			//获取会议室列表
			getTableData: function () {
				// let obj = {
				// 	hysbh: this.search.hysbh
				// };
				this.loading = true;
				getHys().then((res) => {
					console.log(res)
					this.users = res.data;
					console.log(this.users,'user')
					this.loading = false;
				});
			},
			// 通知按钮状态
			buttonDis(row) {
				console.log(row)
				if(row.hyszt=='空闲') {
					return false
				}else{
					return true
				}
			},
			//编辑按钮触发事件
			handleEdit: function (row) {
				this.editFormVisible = true
				this.editForm.hysbh = row.hysbh
				this.editForm.hyszt = row.hyszt
				this.editForm.bz = row.bz
			},
			//通知按钮触发事件
			handleNotice(row) {
				this.noticeFormVisible = true;
				this.noticeForm.hysbh = row.hysbh
			},
			editSubmit: function () {
				// this.$refs.editForm.validate((valid) => {
				// 	if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.editLoading = true;
							//NProgress.start();
							var obj = {
								hysbh: this.editForm.hysbh,
								hyszt: this.editForm.hyszt,
								bz: this.editForm.bz
							}
							// this.$set(obj,'hyszt',this.editForm.hyszt)
							// this.$set(obj,'bz',this.editForm.bz)
							console.log(obj)
							if(obj.hyszt=='空闲') {
								obj.bz = ''
							}
							editHys(obj).then((res) => {
								this.editLoading = false;
								this.$message({
									message: '提交成功',
									type: 'success'
								});
								console.log(obj,'1111')
								// this.$refs['editForm'].resetFields();
								this.editFormVisible = false;
								this.getTableData();
							});
						});
				// 	}
				// });
			},
			noticeSubmit() {
				let obj = {
					hysbh: this.noticeForm.hysbh,
					userId: this.noticeForm.userId,
					time: this.noticeForm.time
				}
				// let obj = Object.assign({}, this.noticeForm);
				sendMessage(obj).then((res) => {
					this.$message({
						message: '提交成功',
						type: 'success'
					});
				});
				this.$refs['noticeForm'].resetFields();
				this.noticeFormVisible = false;
			},
			cancel() {
				this.editFormVisible = false
			},
			cancelNotice() {
				this.noticeFormVisible = false
			}
		},
		mounted() {
			var user = sessionStorage.getItem('user');
			user = JSON.parse(user)
			user.permission=='1'?this.ifAdmin = true:this.ifAdmin = false
			this.getTableData();
			this.getUserData()
		}
	};

</script>

<style scoped>

</style>