'use strict';

var chakram = require('chakram');
var config = require('config');
var log4js = require('log4js');
var expect = chakram.expect;

log4js.configure('./config/log4js.json');
var logger = log4js.getLogger('default');
logger.level = config.log.level;

xdescribe('Board', () => {

  it('GET /api/v1/boards', () => {
    var response = chakram.get(config.host + '/api/v1/boards').then((res) => {
      logger.debug('GET /api/v1/boards');
      logger.debug(res.body);
    });

    expect(response).to.have.status(200);
    expect(response).comprise.of.json({
      count: 9999,
      list: [ {
        id: 9999,
        title: 'TITLE',
        descrription: 'DESC',
        created: 1234567890,
        updated: 1234567890,
        opinionCount: 9999,
        user: {
          id: 9999,
          displayNameType: 'alias',
          displayName: 'Bomb'
        },
        lastOpinion: {
          id: 9999,
          content: '',
          user: {
            id: 9999,
            displayNameType: 'alias',
            displayName: 'Bomb'
          }
        }
      } ]
    });
    return chakram.wait();
  });

  xit('GET /api/v1/boards?max=XXXX&no=XXXX', () => {
    var response = chakram.get(config.host + '/api/v1/boards', {
      qs: {
        max: 2,
        no: 0
      }
    });

    expect(response).to.have.status(200);
    expect(response).to.have.json('count', 0);
    expect(response).to.have.json('boardList', [ {
      id: 0,
      title: 'This is test!',
      description: 'Unihorn test desc.'
    }, {
      id: 1,
      title: 'This is test!',
      description: 'Unihorn test desc.'
    } ]);

    response.then((r) => {
      console.log(r.url);
      console.log(r.body);
    });
    return chakram.wait();
  });

  xit('POST /api/v1/boards', () => {
    var response = chakram.post(config.host + '/api/v1/boards', {
      title: 'This is test!',
      description: 'Unihorn test desc.',
      displayNameType: 'alias'
    }, {
      headers: {
        'Content-Type': 'application/json'
      }
    });

    expect(response).to.comprise.of.json({
      id: 1,
      title: '',
      describe: '',
      created: 1234567890,
      displayNameType: 'alias',
      user: {
        id: 9999,
        displayName: 'Mr.Bomb'
      }
    });

    response.then((r) => {
      console.log(r.body);
    });

    return chakram.wait();
  });

  xit('GET /api/v1/boards/{id}', () => {
    var response = chakram.get(config.host + '/api/v1/boards/1');

    expect(response).to.comprise.of.json({
      id: 1,
      title: '早くアプリを完成させたい',
      describe: 'そのためにもやるべきことをリストしていきます。このボードはボムへいくんが作りました。',
      created: 1234567890,
      updated: 1234567890,
      opinionCount: 9999,
      displayNameType: 'alias',
      user: {
        id: 9999,
        displayName: 'ボムへいくん'
      },
      opinionList: [
        {
          id: 9999,
          content: '',
          user: {
            id: 9999,
            displayName: '',
            displayNameType: ''
          }
        }
      ]
    });

    return chakram.wait();
  });

  xit('POST /api/v1/boards/{id}', () => {
    var response = chakram.put(config.host + '/api/v1/boards/1', {
      content: '新しい意見',
      displayNameType: 'alias'
    }, {
      headers: {
        'Content-Type': 'application/json'
      }
    });

    expect(response).to.comprise.of.json({
      id: 9999,
      content: '新しい意見',
      created: 1234567890,
      displayNameType: 'alias',
      user: {
        id: 9999,
        alias: ''
      }
    });

    return chakram.wait();
  });

  xit('PUT /api/v1/boards/{id}', () => {
    var response = chakram.put(config.host + '/api/v1/boards/1', {
      title: '',
      describe: ''
    }, {
      headers: {
        'Content-Type': 'application/json'
      }
    });

    return chakram.wait();
  });

  xit('GET /api/v1/boards/me', () => {
    var response = chakram.get(config.host + '/api/v1/boards/me');

    expect(response).to.comprise.of.json([ {
      id: 1,
      title: 'title',
      description: 'desc',
      created: 'timestamp',
      updated: 'timestamp',
      boardCount: 9999,
    } ]);

    return chakram.wait();
  });

  xit('GET /api/v1/boards/me?max=XXXX&no=XXXX', () => {
    var response = chakram.get(config.host + '/api/v1/boards/me', {
      qs: {
        max: 10,
        no: 1
      }
    });

    expect(response).to.comprise.of.json({

    });

    return chakram.wait();
  });

});
