<template>
	<div>
		<el-button style="margin:10px 0" type="primary" v-if="ifAdmin" size="small" icon="el-icon-plus" @click="addGg">新增公告</el-button>
		<el-tabs type="border-card">
			<el-tab-pane label="公告栏">
				<el-table :data="tableData">
					<el-table-column prop="ggTitle" label="公告名称" width="150"></el-table-column>
					<el-table-column prop="ggNr" label="公告内容"></el-table-column>
					<el-table-column prop="ggTime" label="公告发布时间" width="120"></el-table-column>
					<el-table-column label="操作" width="150" v-if="ifAdmin">
						<template scope="scope">
							<el-button type="danger" size="small" @click="handleDel(scope.row)">删除</el-button>
						</template>
					</el-table-column>
				</el-table>
			</el-tab-pane>
			<el-tab-pane label="项目进度">
				<template>
					<div>
						<div class="jdt" v-for='(item) in jdArr' :key='item.xmId'>
						<div class="word" style="display:inline">{{item.xmmc}}</div>
						<el-progress :percentage="item.xmjd" style="margin:0 15px" type="circle" :stroke-width="18" :status="item.status"></el-progress>
					</div>
					</div>
				</template>
			</el-tab-pane>
		</el-tabs>
		<el-dialog title="新增" v-model="addFormVisible" :close-on-click-modal="false">
			<el-form :model="addForm" label-width="100px" ref="addForm">
				<el-form-item label="公告名称" prop="ggTitle">
					<el-input v-model="addForm.ggTitle"></el-input>
				</el-form-item>
				<el-form-item label="公告内容">
					<el-input v-model="addForm.ggnr"></el-input>
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
import { getGg,getJd,addGg,delGg } from '../../api/api';
export default {
	data() {
		return {
			textarea: "",
			tableData: [],
			jdArr: [],
			ifAdmin: true,
			addForm: {
				ggTitle: '',
				ggnr: '',
				ggTime: ''
			},
			addFormVisible: false
		}
	},
	methods: {
		getGonggao() {
			var user = sessionStorage.getItem('user');
			user = JSON.parse(user)
			user.permission=='1'?this.ifAdmin = true:this.ifAdmin = false
			getGg().then((res) => {
				this.tableData = res
			});
		},
		getJindu() {
			getJd().then((res) => {
				this.jdArr = res
			});
		},
		addGg() {
			this.addFormVisible = true
		},
		addSubmit() {
			this.addForm.ggTime = new Date().toLocaleDateString()
			var obj = {
				ggTitle: this.addForm.ggTitle,
				ggNr: this.addForm.ggnr,
				// ggTime: this.addForm.ggTime.replace(/\//g,"-"),
				ggTime: util.formatDate.format(new Date(this.addForm.ggTime), 'yyyy-MM-dd')
			}
			addGg(obj).then((res) => {
				this.$message({
					message: res.msg,
					type: 'success'
				});
			})
			this.addFormVisible = false
			this.getGonggao()
		},
		handleDel(row) {
			var obj = {
				ggId: row.ggId
			}
			delGg(obj).then((res) => {
				this.$message({
					message: res.data.msg,
					type: 'success'
				});
			})
			this.getGonggao()
		}
	},
	mounted() {
		this.getGonggao();
		this.getJindu()
	}
}
</script>

<style scoped>
	.el-progress {
		padding: 20px 0;
	}
	.jdt {
		display: inline;
	}
	.word {
		position: relative;
		top: -50px
	}
</style>
