package socialnetwork;

import socialnetwork.config.ApplicationContext;
import socialnetwork.domain.*;
import socialnetwork.domain.validators.PrietenieValidator;
import socialnetwork.domain.validators.UtilizatorValidator;
import socialnetwork.domain.validators.ValidationException;
import socialnetwork.repository.Repository;
import socialnetwork.repository.database.MessageDbRepository;
import socialnetwork.repository.database.PrietenieDbRepository;
import socialnetwork.repository.database.RequestDbRepository;
import socialnetwork.repository.database.UtilizatorDbRepository;
import socialnetwork.repository.file.PrietenieFile;
import socialnetwork.repository.file.UtilizatorFile;
import socialnetwork.service.MessageService;
import socialnetwork.service.PrietenieService;
import socialnetwork.service.RequestService;
import socialnetwork.service.UtilizatorService;

import socialnetwork.ui.Ui;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;


import socialnetwork.domain.validators.ValidatorUi;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {

        MainFX gui = new MainFX();
        gui.main(args);

    }
}


