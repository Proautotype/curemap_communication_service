package com.custard.curemapcommunicationservice.application.usecases.posts;

import com.custard.curemapcommunicationservice.application.commands.UpdatePostReactionCommand;
import com.custard.curemapcommunicationservice.domain.model.Post;

public interface UpdatePostReactionUseCase {
    Post execute(UpdatePostReactionCommand command);
}
