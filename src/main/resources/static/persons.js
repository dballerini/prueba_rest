var persons = [];

function findPerson(personId) {
  return persons[findPersonsKey(personId)];
}

function findPersonsKey (personId) {
  for (var key = 0; key < persons.length; key++) {
    if (persons[key].id == personId) {
      return key;
    }
  }
}

var personService = {
  findAll(fn) {
    axios
      .get('/api/persons')
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  findById(id, fn) {
    axios
      .get('/api/persons/person/' + id)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  create(person, fn) {
    axios
      .post('/api/persons/', person)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  update(id, person, fn) {
    axios
      .put('/api/persons/', person)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  deletePerson(id, fn) {
    axios
      .delete('/api/persons/' + id)
      .then(response => fn(response))
      .catch(error => console.log(error))
  }
}

var List = Vue.extend({
  template: '#person-list',
  data: function () {
    return {persons: [], searchKey: ''};
  },
  computed: {
    filteredPersons() {
      return this.persons.filter((person) => {
      	return person.firstName.indexOf(this.searchKey) > -1 || person.lastName.indexOf(this.searchKey) > -1 || person.aliases.indexOf(this.searchKey) > -1
      })
    }
  },
  mounted() {
    personService.findAll(r => {this.persons = r.data; persons = r.data})
  }
});

var Person = Vue.extend({
  template: '#person',
  data: function () {
    return {person: findPerson(this.$route.params.person_id)};
  }
});

var PersonEdit = Vue.extend({
  template: '#person-edit',
  data: function () {
    return {person: findPerson(this.$route.params.person_id)};
  },
  methods: {
    updatePerson: function () {
      personService.update(this.person.id, this.person, r => router.push('/'))
    }
  }
});

var PersonDelete = Vue.extend({
  template: '#person-delete',
  data: function () {
    return {person: findPerson(this.$route.params.person_id)};
  },
  methods: {
    deletePerson: function () {
      personService.deletePerson(this.person.id, r => router.push('/'))
    }
  }
});

var AddPerson = Vue.extend({
  template: '#add-person',
  data() {
    return {
      person: {firstName: '', lastName: '', aliases:''}
    }
  },
  methods: {
    createPerson() {
      personService.create(this.person, r => router.push('/'))
    }
  }
});

var router = new VueRouter({
	routes: [
		{path: '/', component: List},
		{path: '/person/:person_id', component: Person, name: 'person'},
		{path: '/add-person', component: AddPerson},
		{path: '/person/:person_id/edit', component: PersonEdit, name: 'person-edit'},
		{path: '/person/:person_id/delete', component: PersonDelete, name: 'person-delete'}
	]
});

new Vue({
  router
}).$mount('#app')