#!/usr/bin/perl
# Analyze JVM thread dumps

use strict;

# Global variables
my %thread_stack = {}

# entry point
&main();

sub main {
    my $thread_dump_file = $ARGV[0];
    &parse_file($thread_dump_file);
}

sub parse_file () {
    my $file_name = shift;

    open FILE, $file_name or die $!;

    my $line;
    while ($line = <FILE>) {
        rtrim(ltrim(chomp($line)));
        if ($line) {
            print "$line\n";
        }
    }

    close(FILE);
}

sub trim($) {
	my $string = shift;
	$string =~ s/^\s+//;
	$string =~ s/\s+$//;
	return $string;
}

