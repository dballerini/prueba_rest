var movies = [];

function findMovie(movieId) {
  return movies[findMoviesKey(movieId)];
}

function findMoviesKey (movieId) {
  for (var key = 0; key < movies.length; key++) {
    if (movies[key].id == movieId) {
      return key;
    }
  }
}

var romanize =  function (num) {
    if (isNaN(num))
        return NaN;
    var digits = String(+num).split(""),
        key = ["","C","CC","CCC","CD","D","DC","DCC","DCCC","CM",
               "","X","XX","XXX","XL","L","LX","LXX","LXXX","XC",
               "","I","II","III","IV","V","VI","VII","VIII","IX"],
        roman = "",
        i = 3;
    while (i--)
        roman = (key[+digits.pop() + (i * 10)] || "") + roman;
    return Array(+digits.join("") + 1).join("M") + roman;
}


var movieService = {
  findAll(fn) {
    axios
      .get('/api/movies')
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  findById(id, fn) {
    axios
      .get('/api/movies/movie/' + id)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  create(movie, fn) {
    axios
      .post('/api/movies/', movie)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  update(id, movie, fn) {
    axios
      .put('/api/movies/', movie)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  deleteMovie(id, fn) {
    axios
      .delete('/api/movies/' + id)
      .then(response => fn(response))
      .catch(error => console.log(error))
  }
}

var List = Vue.extend({
  template: '#movie-list',
  data: function () {
    return {movies: [], searchKey: ''};
  },
  computed: {
    filteredMovies() {
      return this.movies.filter((movie) => {
      	return movie.title.indexOf(this.searchKey) > -1
      })
    }
  },
  mounted() {
    movieService.findAll(r => {this.movies = r.data; movies = r.data})
  },
  methods: {
	    romanize : romanize
  }
});

var Movie = Vue.extend({
  template: '#movie',
  data: function () {
    return {movie: findMovie(this.$route.params.movie_id)};
  },
	methods: {
		    romanize : romanize
	}
});

var MovieEdit = Vue.extend({
  template: '#movie-edit',
  data: function () {
    return {movie: findMovie(this.$route.params.movie_id)};
  },
  methods: {
    updateMovie: function () {
      movieService.update(this.movie.id, this.movie, r => router.push('/'))
    }
  }
});

var MovieDelete = Vue.extend({
  template: '#movie-delete',
  data: function () {
    return {movie: findMovie(this.$route.params.movie_id)};
  },
  methods: {
    deleteMovie: function () {
      movieService.deleteMovie(this.movie.id, r => router.push('/'))
    }
  }
});

var AddMovie = Vue.extend({
  template: '#add-movie',
  data() {
    return {
      movie: {title: '', releaseYear: ''}
    }
  },
  methods: {
    createMovie() {
      movieService.create(this.movie, r => router.push('/'))
    }
  }
});

var router = new VueRouter({
	routes: [
		{path: '/', component: List},
		{path: '/movie/:movie_id', component: Movie, name: 'movie'},
		{path: '/add-movie', component: AddMovie},
		{path: '/movie/:movie_id/edit', component: MovieEdit, name: 'movie-edit'},
		{path: '/movie/:movie_id/delete', component: MovieDelete, name: 'movie-delete'}
	]
});

new Vue({
  router
}).$mount('#app')