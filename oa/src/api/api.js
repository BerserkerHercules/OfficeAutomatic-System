import axios from 'axios';

let base = 'http://localhost:8080/';

export const requestLogin = params => { return axios.post(`${base}/user/login`, params).then(res => res.data); };
//公告
export const getGg = params => { return axios.post(`${base}/gsxx/gsgg`, params).then(res => res.data); };
//新增公告
export const addGg = params => { return axios.post(`${base}/gsxx/addGg`, params).then(res => res.data); };
//删除公告
export const delGg = params => { return axios.post(`${base}/gsxx/delGg`, params).then(res => res.data); };
//项目进度
export const getJd = params => { return axios.post(`${base}/gsxx/xmjd`, params).then(res => res.data); };
//我的任务
export const getRw = params => { return axios.post(`${base}/rwxx/myRw`, params).then(res => res.data); };

export const getUserList = params => { return axios.get(`${base}/user/userList`, { params: params }); };

export const getUserListPage = params => { return axios.get(`${base}/user/listpage`, { params: params }); };

export const removeUser = params => { return axios.get(`${base}/user/remove`, { params: params }); };

export const batchRemoveUser = params => { return axios.get(`${base}/user/batchremove`, { params: params }); };
//修改任务
export const editUser = params => { return axios.get(`${base}/rwxx/updateRw`, { params: params }); };
//新增任务
export const addUser = params => { return axios.get(`${base}/rwxx/addRw`, { params: params }); };
//获取请假数据
export const getQjList = params => { return axios.get(`${base}/qjsq/getQjList`, { params: params }); };
//请假申请
export const addQj = params => { return axios.get(`${base}/qjsq/addQjsq`, { params: params }); };
//请假审批
export const agreeQj = params => { return axios.get(`${base}/qjsq/agreeQj`, { params: params }); };
//获取日志
export const getLog = params => { return axios.get(`${base}/rwxx/rzList`, { params: params }); };
//新增日志
export const addLog = params => { return axios.get(`${base}/rwxx/addRz`, { params: params }); };
//获取下载文件信息
export const getDown = params => { return axios.get(`${base}/file/fileList`, { params: params }); };
//下载文件
export const downFile = params => { return axios.get(`${base}/file/download`, { params: params }); };
//删除文件
export const delFile = params => { return axios.get(`${base}/file/deleteFile`, { params: params }); };
//获取会议室
export const getHys = params => { return axios.get(`${base}/user/getHysList`, { params: params }); };
//编辑会议室信息
export const editHys = params => { return axios.get(`${base}/user/updateHys`, { params: params }); };
//发送参会短信
export const sendMessage = params => { return axios.post(`${base}/user/kh`,  params); };
