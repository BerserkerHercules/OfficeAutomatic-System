<template>
<div>
	<el-form :model="search" style="display:inline">
		<el-form-item>
			<el-col :span="5">
				<el-date-picker
					v-model="search.rzTime"
					type="date"
					format="yyyy-MM-dd"
					value-format="yyyy-MM-dd"
					placeholder="选择日期"
					clearable="true"
					@change="dateChange">
				</el-date-picker>
			</el-col>
			<el-col :span="10">
				<el-button type="primary" size="small" icon="el-icon-search" @click="getData">查询</el-button>
				<el-button type="primary" size="small" icon="el-icon-plus" @click="addRz">新增</el-button>
			</el-col>
		</el-form-item>
	</el-form>
	<el-table :data='tableData' style="width: 100%;">
		<el-table-column prop="userName" label="记录人" width="120" sortable>
		</el-table-column>
		<el-table-column prop="rdnr" label="日志内容">
		</el-table-column>
		<el-table-column prop="rzTime" label="记录时间" width="120" sortable>
		</el-table-column>
	</el-table>
	<el-dialog title="新增日志" :visible.sync="dialogVisible">
		<el-form :model="add">
			<el-form-item label="日志内容">
				<el-input :rows="5" type="textarea" v-model="add.rdnr"></el-input>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" size="small" @click="submit">提交</el-button>
				<el-button size="small" @click="cancel">取消</el-button>
			</el-form-item>
		</el-form>
	</el-dialog>
</div>
</template>

<script>
import { getLog, addLog } from '../../api/api'
export default {
	data() {
		return {
			tableData: [],
			dialogVisible: false,
			logForm: {
				logArea: ''
			},
			search: {
				rzTime: ''
			},
			add: {
				rdnr: ''
			}
		}
	},
	mounted() {
		this.getData()
	},
	methods: {
		getData() {
			var obj = {
				rzTime: this.search.rzTime
			}
			getLog(obj).then((res => {
				this.tableData = res.data
			}))
		},
		dateChange(val) {
			this.search.rzTime = val
		},
		addRz() {
			this.dialogVisible = true
			this.add.rdnr = ''
		},
		submit() {
			var user = sessionStorage.getItem('user');
			user = JSON.parse(user)
			var obj = {
				userId: user.userId,
				rzTime: new Date().toLocaleDateString(),
				rdnr: this.add.rdnr
			}
			addLog(obj).then((res) => {
				this.$message({
          message: res.data.msg,
          type: 'success'
        });
			})
			this.dialogVisible = false
			this.getData()
		},
		cancel() {
			this.dialogVisible = false
			this.add.rdnr = ''
		}
	},
}
</script>
