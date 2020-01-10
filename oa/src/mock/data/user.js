import Mock from 'mockjs';
const LoginUsers = [
  {
    id: 1,
    username: 'admin',
    password: '123456',
    // avatar: 'https://raw.githubusercontent.com/taylorchen709/markdown-images/master/vueadmin/user.png',
    name: '郑佳伟'
  }
];

const Users = [];
const pool = {
  lower: "abcdefghijklmnopqrstuvwxyz",
  upper: "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
  number: "0123456789",
  symbol: "!@#$%^&*()[]"
}

for (let i = 0; i < 86; i++) {
  Users.push(Mock.mock({
    id: Mock.Random.guid(),
    roomId: Mock.Random.character('lower') + Mock.Random.string( 'number', 5 ),
    name: Mock.Random.cname(),
    urgent: Mock.Random.natural(1, 3),
    status: Mock.Random.natural(0, 1),
    'age|18-60': 1,
    birth: Mock.Random.date(),
    taskName: Mock.Random.cword(3, 7),
    remarks: Mock.Random.cword(5, 15)
  }));
}

export { LoginUsers, Users };
