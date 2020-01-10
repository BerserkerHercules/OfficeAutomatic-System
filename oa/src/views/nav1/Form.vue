<template>
	<div>
		<el-form>
			<el-form-item>
				<el-button v-if="!ifAdmin" type="primary" size="small" @click="apply" icon="el-icon-plus">请假申请</el-button>
			</el-form-item>
		</el-form>
		<el-table :data="tableData">
			<el-table-column prop="userName" label="请假人" width="150"></el-table-column>
			<el-table-column prop="qjyy" label="请假类型"></el-table-column>
			<el-table-column prop="ksTime" label="请假开始时间" width="180"></el-table-column>
			<el-table-column prop="jsTime" label="请假结束时间" width="180"></el-table-column>
			<el-table-column prop="qjzt" label="审批状态" width="180"></el-table-column>
			<el-table-column prop="qjms" label="备注" width="180"></el-table-column>
			<el-table-column label="操作" width="150" v-if="ifAdmin">
				<template scope="scope">
					<el-button type="success" size="small" :disabled="buttonAble(scope.row)" @click="handleAgree(scope.row,1)">同意</el-button>
					<el-button type="danger" size="small" :disabled="buttonAble(scope.row)" @click="handleAgree(scope.row,0)">拒绝</el-button>
				</template>
			</el-table-column>
		</el-table>
		<el-dialog title="新增" v-model="addFormVisible" :close-on-click-modal="false">
			<el-form :model="addForm" label-width="100px" ref="addForm">
				<el-form-item label="请假人" prop="UserName">
					<el-input v-model="addForm.userName" readonly=true></el-input>
				</el-form-item>
				<el-form-item label="请假类型">
					<el-select v-model="addForm.qjyy">
						<el-option label="病假" value="病假"></el-option>
						<el-option label="事假" value="事假"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="请假时间">
					<el-date-picker
						v-model="addForm.time"
						type="daterange"
						value-format="yyyy-MM-dd"
						range-separator="至"
						start-placeholder="开始日期"
						end-placeholder="结束日期">
					</el-date-picker>
				</el-form-item>
				<el-form-item label="备注">
					<el-input type="textarea" v-model="addForm.qjms"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button size="small" @click.native="addFormVisible = false">取消</el-button>
				<el-button size="small" type="primary" @click.native="addSubmit">提交</el-button>
			</div>
		</el-dialog>
	</div>
</template>

<script>
import util from '../../common/js/util'
import { getQjList,agreeQj,addQj } from '../../api/api'
	export default {
		data() {
			return {
				tableData: [],
				ifAdmin: true,
				addFormVisible: false,
				addForm: {
					userName: '',
					qjyy: '',
					time: '',
					qjms: ''
				}
			}
		},
		mounted() {
			this.getTableData()
		},
		methods: {
			handleAgree(row,value) {
				console.log(row,value)
				if(value==1) {
					var params = {
						qjId: row.qjId,
						qjzt: '同意'
					}
				}else{
					var params = {
						qjId: row.qjId,
						qjzt: '拒绝'
					}
				}
				agreeQj(params).then((res) => {
					this.$message({
						message: res.data.msg,
						// type: 'success'
					});
					this.getTableData()
				})
			},
			getTableData() {
				var user = sessionStorage.getItem('user');
				user = JSON.parse(user)
				user.permission=='1'?this.ifAdmin = true:this.ifAdmin = false
				var params = {
					userId: user.userId
				}
				getQjList(params).then((res) => {
					this.tableData = res.data
				})
			},
			buttonAble(row) {
				if(row.qjzt=='审批中') {
					return false
				} else {
					return true
				}
			},
			apply() {
				var user = sessionStorage.getItem('user');
				user = JSON.parse(user)
				this.addFormVisible = true
				this.addForm.userName = user.userName
				this.addForm.qjyy = ''
				this.addForm.time = ''
				this.addForm.qjms = ''
			},
			addSubmit() {
				var user = sessionStorage.getItem('user');
				user = JSON.parse(user)
				var obj = {
					userId: user.userId,
					qjyy: this.addForm.qjyy,
					// ksTime: this.addForm.time[0].valueOf(),
					ksTime: (!this.addForm.time[0] || this.addForm.time[0] == '') ? '' : util.formatDate.format(new Date(this.addForm.time[0]), 'yyyy-MM-dd'),
					// jsTime: this.addForm.time[1].valueOf(),
					jsTime: (!this.addForm.time[1] || this.addForm.time[1] == '') ? '' : util.formatDate.format(new Date(this.addForm.time[1]), 'yyyy-MM-dd'),
					qjzt: '审批中',
					qjms: this.addForm.qjms
				}
				addQj(obj).then((res) => {
					this.$message({
						message: res.data.msg,
						type: 'success'
        	});
				})
				this.addFormVisible = false
				this.getTableData()
			}
		}
	}

</script>