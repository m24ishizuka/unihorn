'use strict';

var chakram = require('chakram');
var config = require('config');
var log4js = require('log4js');
var os = require('os');

var expect = chakram.expect;
log4js.configure('./config/log4js.json');
var logger = log4js.getLogger('default');
logger.level = config.log.level;

xdescribe('User', () => {

  it('POST /api/v1/users', () => {
    var response = chakram.post(config.host + '/api/v1/users', {
      password: 'Password',
      alias: 'Nerson',
      defaultDisplayNameType: 'alias'
    }, {
      headers: {
        'Content-Type': 'application/json'
      }
    }).then((res) => {
      logger.debug('POST /api/v1/users' + os.EOL + res.body);
    });

    expect(response).to,comprise.of.json({
      id: 1,
      name: '名前',
      alias: 'Nerson',
      defaultDisplayNameType: 'alias'
    });

    return chakram.wait();
  });

  it('GET /api/v1/users/me', () => {
    var response = chakram.get(config.host + '/api/v1/users/me');

    expect(response).to.have.status(200);
    expect(response).to.have.json('id', 1);
    expect(response).to.have.json('name', 'testuser');
    expect(response).to.have.json('alias', 'ali');

    expect(response).not.to.have.json('password');

    return chakram.wait();
  });

});
