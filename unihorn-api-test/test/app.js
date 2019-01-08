'use strict';

var chakram = require('chakram');
var config = require('config');
var expect = chakram.expect;

xdescribe('App', () => {

  it('GET /api/v1/app', () => {
    var response = chakram.get(config.host + '/api/v1/app');

    expect(response).to.have.status(200);
    expect(response).to.have.json('version', '1.0.0.00');

    return chakram.wait();
  });

});
