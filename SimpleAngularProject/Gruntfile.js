/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
module.exports = function (grunt) {
        
    var appConfig = {
        app: require('./bower.json').appPath || 'app',
        dist: 'dist'
    };
    // Project configuration.
    grunt.initConfig({
          
        project: appConfig,
        
        watch: {
            gruntfile: {
              files: ['Gruntfile.js']
            },
            livereload: {
              options: {
                livereload: '<%= connect.options.livereload %>'
              },
              files: [
                '<%= project.app %>/{,*/}*.html',
                '<%= project.app %>/views/{,*/}*.html',
                '<%= project.app %>/styles/{,*/}*.css',
                '<%= project.app %>/scripts/{,*/}*.js',
                '<%= project.app %>/factory/{,*/}*.js',
                '<%= project.app %>/controllers/{,*/}*.js'
              ]
            }
        }, // watch
                
        connect: {
            options: {
                port: 9000,
                // Mudar para '0.0.0.0' para acesso de fora
                hostname: 'localhost',
                livereload: 35729
            },
            livereload: {
                options: {
                  open: true,
                  middleware: function (connect) {
                    return [
                      connect.static('.tmp'),
                      connect().use(
                        '/bower_components',
                        connect.static('./bower_components')
                      ),
                      connect().use(
                        '/app/styles',
                        connect.static('./app/styles')
                      ),
                      connect.static(appConfig.app)
                    ];
                  }
                }
            }
        }, //connect
        
        clean: {
            dist: {
              files: [{
                dot: true,
                src: [
                  '<%= project.dist %>/{,*/}*'
                ]
              }]
            },
            server : '.tmp'
          }, // clean
        
        // Copia os arquivos para o lugar onde as demais tarefas irão usar
        copy: {
            dist: {
                files: [{
                   expand: true,
                   dot: true,
                   cwd: '<%= project.app %>',
                   dest: '<%= project.dist %>',
                   src: [
                       '*.html',
                       'views/{,*/}*.html',
                       'scripts/*.js',
                       'styles/*.css'
                   ]
                }]
            }
        }, // copy
        
        // Renomeia arquivos para controle de cache do browser
        filerev: {
          dist: {
            src: [
              '<%= project.dist %>/scripts/{,*/}*.js',
              '<%= project.dist %>/styles/{,*/}*.css'
            ]
          }
        }, // filerev
        
        // Minifica os arquivos html (páginas ou views (angular))
        htmlmin: {
            dist: {
                options: {
                    collapseWhitespace: true,
                    conservativeCollapse: true,
                    collapseBooleanAttributes: true,
                    removeCommentsFromCDATA: true,
                    removeOptionalTags: true
                },
                files: [{
                    expand: true,
                    cwd: '<%= project.dist %>',
                    src: ['*.html', 'views/{,*/}*.html'],
                    dest: '<%= project.dist %>'
                }]
            }
        }, // htmlmin
        
        useminPrepare: {
            html: '<%= project.app %>/index.html',
            options: {
                dest: '<%= project.dist %>',
                flow: {
                html: {
                  steps: {
                    js: ['concat', 'uglifyjs'],
                    css: ['cssmin']
                  },
                  post: {}
                }
              }
            }
        }, // useminPrepare
        
        usemin: {
            html: ['<%= project.dist %>/{,*/}*.html'],
            css: ['<%= project.dist %>/styles/{,*/}*.css'],
            js: ['<%= project.dist %>/scripts/{,*/}*.js'],
            options: {
              assetsDirs: [
                '<%= project.dist %>',
                '<%= project.dist %>/styles',
                '<%= project.dist %>/scripts'
              ]
            }
       } // usemin
        
    });
        
    //Carrega plugins
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-cssmin');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-htmlmin');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-connect');
    grunt.loadNpmTasks('grunt-filerev');
    grunt.loadNpmTasks('grunt-usemin');
    
    //Tarefas
    grunt.registerTask('serve', [
        'clean:server',
        'connect',
        'watch'
    ]);
        
    grunt.registerTask('build', [
        'clean:dist', 
        'useminPrepare', 
        'copy:dist', 
        'concat',
        'cssmin', 
        'uglify', 
        'filerev', 
        'usemin',
        'htmlmin'
    ]);
    
};
