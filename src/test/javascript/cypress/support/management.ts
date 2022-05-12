Cypress.Commands.add('getManagementInfo', () => {
  return cy
    .request({
      method: 'GET',
      url: '/management/info',
    })
    .then(response => response.body);
});

declare global {
  namespace Cypress {
    interface Chainable {
      getManagementInfo(): Cypress.Chainable;
    }
  }
}

export {};
